package com.tech.futureteric.feedbacklibrary.ui.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.R2;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;
import com.tech.futureteric.feedbacklibrary.utils.CheckingInputsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.utils.CheckingInputsUtils.checkFeedbackSystemActivityBundle;

public class FeedbackSystemActivity extends AppCompatActivity {

    // TODO test butterknife binding is working well within the library
    @BindView(R2.id.button_fAQ) Button mFAQ_button;
    @BindView(R2.id.button_featureRequest) Button mFeatureRequest_button;
    @BindView(R2.id.button_generalFeedback) Button mGeneralFeedback_button;
    @BindView(R2.id.button_bugReport) Button mBugReport_button;
    @BindView(R2.id.button_contactUs) Button mContactUs_button;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_system);
        ButterKnife.bind(this);
        setupSections();
    }

    private void setupSections() {
        Bundle bundle = getIntent().getExtras();
        checkFeedbackSystemActivityBundle(bundle);

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