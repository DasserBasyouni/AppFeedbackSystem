package com.tech.futureteric.feedbacklibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import java.util.ArrayList;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.utils.CheckingInputsUtils.checkFeedbackSystemActivityBundle;

public class FeedbackSystemBuilder{

    private @LibEnums.Sections int[] mSections;
    private Bundle bundle;

    public FeedbackSystemBuilder() {}

    public  FeedbackSystemBuilder withSections(@LibEnums.Sections int[] sections){
        mSections = sections;
        return this;
    }

    public FeedbackSystemBuilder addSection(Section section) {
        bundle = getFeedbackSystemBundle();

        if (section instanceof Section.FrequentlyAskedQuestions) {
            bundle.putStringArrayList(BUNDLE_FAQ_LIST,
                    (ArrayList<String>) ((Section.FrequentlyAskedQuestions) section).questionsAndAnswersList);

        } else if (section instanceof Section.FeatureRequest) {
            // TODO make Section.FeatureRequest functional
           /* bundle.putStringArrayList(BUNDLE_FAQ_LIST,
                    (ArrayList<String>) ((Section.FeatureRequest) section).questionsAndAnswers);*/

        }  else if (section instanceof Section.GeneralFeedback) {
            bundle.putString(BUNDLE_GENERAL_FEEDBACK_EMAIL, ((Section.GeneralFeedback) section).email);

        } else if (section instanceof Section.BugReport) {
            bundle.putString(BUNDLE_BUG_REPORT_EMAIL, ((Section.BugReport) section).email);

        } else if (section instanceof Section.ContactUs) {
            bundle.putString(BUNDLE_CONTACT_US_EMAIL, ((Section.ContactUs) section).email);

        } else
            throw new IllegalArgumentException("Unknown Section Type: " + section);

        return this;
    }

    public void buildThenShowDialog(Context context){
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title("Feedback")
                .customView(R.layout.dialog_feedback_system, true)
                .show();

        assert dialog.getCustomView() != null;
        setupDialogView(context, bundle, dialog.getCustomView());
    }

    private void setupDialogView(final Context context, Bundle bundle, View view) {
        checkFeedbackSystemActivityBundle(bundle);

        Button mFAQ_button = view.findViewById(R.id.button_fAQ);
        Button mFeatureRequest_button = view.findViewById(R.id.button_featureRequest);
        Button mGeneralFeedback_button = view.findViewById(R.id.button_generalFeedback);
        Button mBugReport_button = view.findViewById(R.id.button_bugReport);
        Button mContactUs_button = view.findViewById(R.id.button_contactUs);

        View.OnClickListener listener = buttonView -> {
            bundle.putString(BUNDLE_CLICKED_SECTION, buttonView.getTag().toString());

            Intent i = new Intent(context, FeedbackSystemActivity.class);
            i.putExtras(bundle);
            context.startActivity(i);
        };

        @LibEnums.Sections int[] sections = bundle.getIntArray(BUNDLE_SECTIONS);
        assert sections != null;
        for (int section : sections) {
            switch (section) {
                case LibEnums.ALL:
                    mFAQ_button.setVisibility(View.VISIBLE);
                    mFAQ_button.setOnClickListener(listener);
                    mFeatureRequest_button.setVisibility(View.VISIBLE);
                    mFeatureRequest_button.setOnClickListener(listener);
                    mGeneralFeedback_button.setVisibility(View.VISIBLE);
                    mGeneralFeedback_button.setOnClickListener(listener);
                    mBugReport_button.setVisibility(View.VISIBLE);
                    mBugReport_button.setOnClickListener(listener);
                    mContactUs_button.setVisibility(View.VISIBLE);
                    mContactUs_button.setOnClickListener(listener);
                    break;

                case LibEnums.FREQUENTLY_ASKED_QUESTIONS:
                    mFAQ_button.setVisibility(View.VISIBLE);
                    mFAQ_button.setOnClickListener(listener);
                    break;

                case LibEnums.FEATURE_REQUEST:
                    mFeatureRequest_button.setVisibility(View.VISIBLE);
                    mFeatureRequest_button.setOnClickListener(listener);
                    break;

                case LibEnums.GENERAL_FEEDBACK:
                    mGeneralFeedback_button.setVisibility(View.VISIBLE);
                    mGeneralFeedback_button.setOnClickListener(listener);
                    break;

                case LibEnums.BUG_REPORT:
                    mBugReport_button.setVisibility(View.VISIBLE);
                    mBugReport_button.setOnClickListener(listener);
                    break;

                case LibEnums.CONTACT_US:
                    mContactUs_button.setVisibility(View.VISIBLE);
                    mContactUs_button.setOnClickListener(listener);
                    break;
            }
        }
    }

    private Bundle getFeedbackSystemBundle() {
        if (bundle == null) {
            Bundle bundle = new Bundle();
            bundle.putIntArray(BUNDLE_SECTIONS, mSections);
        }
        return bundle;
    }
}