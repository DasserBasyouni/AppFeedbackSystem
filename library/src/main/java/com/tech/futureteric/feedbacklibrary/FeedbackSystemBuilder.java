package com.tech.futureteric.feedbacklibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.utils.CheckingInputsUtils.checkFeedbackSystemActivityBundle;

public class FeedbackSystemBuilder {

    private @LibEnums.Sections
    int[] mSections;

    public FeedbackSystemBuilder() {}

    public  FeedbackSystemBuilder withSections(@LibEnums.Sections int[] sections){
        mSections = sections;
        return this;
    }

    public void buildThenShowDialog(Activity activity){
        MaterialDialog dialog = new MaterialDialog.Builder(activity)
                .title("Feedback")
                .customView(R.layout.dialog_feedback_system, true)
                .show();

        // TODO test this function working without crashing
        assert dialog.getCustomView() != null;
        setupView(getFeedbackSystemBundle(), dialog.getCustomView());
    }

    private void setupView(Bundle bundle, View view) {
        checkFeedbackSystemActivityBundle(bundle);

        Button mFAQ_button = view.findViewById(R.id.button_fAQ);
        Button mFeatureRequest_button = view.findViewById(R.id.button_featureRequest);
        Button mGeneralFeedback_button = view.findViewById(R.id.button_generalFeedback);
        Button mBugReport_button = view.findViewById(R.id.button_bugReport);
        Button mContactUs_button = view.findViewById(R.id.button_contactUs);

        mFAQ_button.setVisibility(View.VISIBLE);

        @LibEnums.Sections int[] sections = bundle.getIntArray(BUNDLE_SECTIONS);
        assert sections != null;
        for (int section : sections) {
            switch (section) {
                case LibEnums.ALL:
                    mFAQ_button.setVisibility(View.VISIBLE);
                    mFeatureRequest_button.setVisibility(View.VISIBLE);
                    mGeneralFeedback_button.setVisibility(View.VISIBLE);
                    mBugReport_button.setVisibility(View.VISIBLE);
                    mContactUs_button.setVisibility(View.VISIBLE);
                    break;

                case LibEnums.FREQUENTLY_ASKED_QUESTIONS:
                    mFAQ_button.setVisibility(View.VISIBLE);
                    break;

                case LibEnums.FEATURE_REQUEST:
                    mFeatureRequest_button.setVisibility(View.VISIBLE);
                    break;

                case LibEnums.GENERAL_FEEDBACK:
                    mGeneralFeedback_button.setVisibility(View.VISIBLE);
                    break;

                case LibEnums.BUG_REPORT:
                    mBugReport_button.setVisibility(View.VISIBLE);
                    break;

                case LibEnums.CONTACT_US:
                    mContactUs_button.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private Bundle getFeedbackSystemBundle() {
        Bundle bundle = new Bundle();
        // TODO test casting array to parcelable is not crashing
        bundle.putIntArray(BUNDLE_SECTIONS, mSections);
        return bundle;
    }
}
