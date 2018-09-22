package com.tech.futureteric.feedbacklibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tech.futureteric.feedbacklibrary.adapter.FeedbackDialogAdapter;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_USER_UID;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;

public class FeedbackSystemBuilder {

    private List<String> mSections;
    private Bundle bundle;

    public FeedbackSystemBuilder() {
    }


    public FeedbackSystemBuilder addSection(Section section) {
        if (bundle == null)
            bundle = new Bundle();
        bundle.putStringArrayList(BUNDLE_SECTIONS, (ArrayList<String>) mSections);

        if (mSections == null)
            mSections = new ArrayList<>();
        mSections.add(section.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2"));

        if (section instanceof Section.FrequentlyAskedQuestions) {
            bundle.putStringArrayList(BUNDLE_FAQ_LIST, (ArrayList<String>)
                    ((Section.FrequentlyAskedQuestions) section).questionsAndAnswersList);

        } else if (section instanceof Section.FeatureRequest) {
            // TODO make Section.Forum functional
            EventBus.getDefault().postSticky( ((Section.FeatureRequest) section).firebaseFirestore );
            Log.e("Z_", "posted");
            bundle.putInt(BUNDLE_FEATURE_REQUEST_USER_UID, ((Section.FeatureRequest) section).userUid);

        } else if (section instanceof Section.GeneralFeedback) {
            bundle.putString(BUNDLE_GENERAL_FEEDBACK_EMAIL, ((Section.GeneralFeedback) section).email);

        } else if (section instanceof Section.BugReport) {
            bundle.putString(BUNDLE_BUG_REPORT_EMAIL, ((Section.BugReport) section).email);

        } else if (section instanceof Section.ContactUs) {
            bundle.putString(BUNDLE_CONTACT_US_EMAIL, ((Section.ContactUs) section).email);

        } else
            throw new IllegalArgumentException("Unknown Section Type: " + section);

        return this;
    }

    public void buildThenShowDialog(Context context) {
        if (mSections.size() > 1)
            new MaterialDialog.Builder(context)
                    .title("Feedback")
                    .adapter(new FeedbackDialogAdapter(mSections, bundle), new LinearLayoutManager(context))
                    .show();
        else {
            bundle.putString(BUNDLE_CLICKED_SECTION, mSections.get(0));
            Intent intent = new Intent(context, FeedbackSystemActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

}