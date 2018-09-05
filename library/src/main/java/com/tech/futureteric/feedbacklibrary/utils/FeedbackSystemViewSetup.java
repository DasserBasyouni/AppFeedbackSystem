package com.tech.futureteric.feedbacklibrary.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tech.futureteric.feedbacklibrary.R2;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.utils.CheckingInputsUtils.checkFeedbackSystemActivityBundle;

public class FeedbackSystemViewSetup {

    // TODO test butterknife binding is working well within the library
    @BindView(R2.id.button_fAQ) Button mFAQ_button;
    @BindView(R2.id.button_featureRequest) Button mFeatureRequest_button;
    @BindView(R2.id.button_generalFeedback) Button mGeneralFeedback_button;
    @BindView(R2.id.button_bugReport) Button mBugReport_button;
    @BindView(R2.id.button_contactUs) Button mContactUs_button;

    public void setupView(Activity activity, Bundle bundle, View view) {
        checkFeedbackSystemActivityBundle(bundle);

        if (view == null)
            ButterKnife.bind(activity);
        else
            ButterKnife.bind(activity, view);

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
}
