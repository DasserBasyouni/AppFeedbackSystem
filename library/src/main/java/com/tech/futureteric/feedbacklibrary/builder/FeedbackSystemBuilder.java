package com.tech.futureteric.feedbacklibrary.builder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tech.futureteric.feedbacklibrary.Section;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackDialogFragment;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CUSTOM_COLORS_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ENABLE_COLORED_STATUS;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ENABLE_COLORFUL_FEEDBACK;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_USER_UID;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;

public class FeedbackSystemBuilder {

    private String dialogTitle, dialogSentence;
    private List<String> mSections;
    private Bundle mBundle;

    public FeedbackSystemBuilder() {}


    public FeedbackSystemBuilder addSection(Section section) {
        getBundle().putStringArrayList(BUNDLE_SECTIONS, (ArrayList<String>) mSections);

        if (mSections == null)
            mSections = new ArrayList<>();
        mSections.add(section.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2"));

        if (section instanceof Section.FrequentlyAskedQuestions) {
            Section.FrequentlyAskedQuestions frequentlyAskedQuestions = (Section.FrequentlyAskedQuestions) section;
            mBundle.putStringArrayList(BUNDLE_FAQ_LIST,
                    (ArrayList<String>) frequentlyAskedQuestions.questionsAndAnswersList);
            if (frequentlyAskedQuestions.theme != 0)
                mBundle.putInt(BUNDLE_FAQ_THEME, frequentlyAskedQuestions.theme);

        } else if (section instanceof Section.FeatureRequest) {
            Section.FeatureRequest featureRequest = (Section.FeatureRequest) section;
            EventBus.getDefault().postSticky(featureRequest.firebaseFirestore);
            mBundle.putInt(BUNDLE_FEATURE_REQUEST_USER_UID, featureRequest.userUid);
            if (featureRequest.theme != 0)
                mBundle.putInt(BUNDLE_FEATURE_REQUEST_THEME, featureRequest.theme);

        } else if (section instanceof Section.GeneralFeedback) {
            Section.GeneralFeedback generalFeedback = (Section.GeneralFeedback) section;
            mBundle.putString(BUNDLE_GENERAL_FEEDBACK_EMAIL, ((Section.GeneralFeedback) section).email);
            if (generalFeedback.theme != 0)
                mBundle.putInt(BUNDLE_GENERAL_FEEDBACK_THEME, generalFeedback.theme);

        } else if (section instanceof Section.BugReport) {
            Section.BugReport bugReport = (Section.BugReport) section;
            mBundle.putString(BUNDLE_BUG_REPORT_EMAIL, bugReport.email);
            if (bugReport.theme != 0)
                mBundle.putInt(BUNDLE_BUG_REPORT_THEME, bugReport.theme);

        } else if (section instanceof Section.ContactUs) {
            Section.ContactUs contactUs = (Section.ContactUs) section;
            mBundle.putString(BUNDLE_CONTACT_US_EMAIL, contactUs.email);
            if (contactUs.theme != 0)
                mBundle.putInt(BUNDLE_CONTACT_US_THEME, contactUs.theme);

        } else
            throw new IllegalArgumentException("Unknown Section Type: " + section);

        return this;
    }

    public FeedbackSystemBuilder dialogWithTitleAndSentence(String title, String sentence) {
        dialogTitle = title;
        dialogSentence = sentence;
        return this;
    }

    public void buildThenShowDialog(AppCompatActivity activity) {
        if (mSections.size() > 1) {
            FeedbackDialogFragment feedbackDialog = FeedbackDialogFragment
                    .newInstance(dialogTitle, dialogSentence, mSections, mBundle);
            feedbackDialog.setCancelable(false);
            feedbackDialog.show(activity.getSupportFragmentManager(), null);

        }else {
            mBundle.putString(BUNDLE_CLICKED_SECTION, mSections.get(0));
            Intent intent = new Intent(activity, FeedbackSystemActivity.class);
            intent.putExtras(mBundle);
            activity.startActivity(intent);
        }
    }

    public FeedbackSystemBuilder enableColorfulFeedback(boolean colorfulButtons) {
        getBundle().putBoolean(BUNDLE_ENABLE_COLORFUL_FEEDBACK, colorfulButtons);
        return this;
    }

    public FeedbackSystemBuilder setCustomColorfulFeedback(List<Integer> customColorsList) {
        getBundle().putBoolean(BUNDLE_ENABLE_COLORFUL_FEEDBACK, true);
        getBundle().putIntegerArrayList(BUNDLE_CUSTOM_COLORS_LIST, (ArrayList<Integer>) customColorsList);
        return this;
    }

    private Bundle getBundle() {
        if (mBundle == null)
            mBundle = new Bundle();
        return mBundle;
    }

    public FeedbackSystemBuilder enableColoredStatusBar() {
        getBundle().putBoolean(BUNDLE_ENABLE_COLORED_STATUS, true);
        return this;
    }
}