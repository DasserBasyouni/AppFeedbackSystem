package com.tech.futureteric.feedbacklibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_RECEIVER_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.utils.CheckingInputsUtils.checkFeedbackSystemActivityBundle;

public class FeedbackSystemBuilder {

    private @LibEnums.Sections int[] mSections;
    private String mEmail;

    public FeedbackSystemBuilder() {}

    public  FeedbackSystemBuilder withSections(@LibEnums.Sections int[] sections){
        mSections = sections;
        return this;
    }

    public FeedbackSystemBuilder setReceiverEmail(String email){
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
            throw new IllegalArgumentException("FeedbackSystemBuilder: Invalid receiver email");
        mEmail = email;
        return this;
    }

    public void buildThenShowDialog(Activity activity){
        MaterialDialog dialog = new MaterialDialog.Builder(activity)
                .title("Feedback")
                .customView(R.layout.dialog_feedback_system, true)
                .show();

        assert dialog.getCustomView() != null;
        setupDialogView(activity, getFeedbackSystemBundle(), dialog.getCustomView());
    }

    private void setupDialogView(final Activity activity, Bundle bundle, View view) {
        checkFeedbackSystemActivityBundle(bundle);

        Button mFAQ_button = view.findViewById(R.id.button_fAQ);
        Button mFeatureRequest_button = view.findViewById(R.id.button_featureRequest);
        Button mGeneralFeedback_button = view.findViewById(R.id.button_generalFeedback);
        Button mBugReport_button = view.findViewById(R.id.button_bugReport);
        Button mContactUs_button = view.findViewById(R.id.button_contactUs);

        View.OnClickListener listener = buttonView -> {
            bundle.putString(BUNDLE_CLICKED_SECTION, buttonView.getTag().toString());

            Intent i = new Intent(activity, FeedbackSystemActivity.class);
            i.putExtras(bundle);
            activity.startActivity(i);
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
        Bundle bundle = new Bundle();
        bundle.putIntArray(BUNDLE_SECTIONS, mSections);
        bundle.putString(BUNDLE_RECEIVER_EMAIL, mEmail);
        return bundle;
    }
}
