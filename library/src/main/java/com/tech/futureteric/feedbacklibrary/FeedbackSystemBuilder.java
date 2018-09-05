package com.tech.futureteric.feedbacklibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;
import com.tech.futureteric.feedbacklibrary.ui.butterknife.FeedbackSystemActivity;
import com.tech.futureteric.feedbacklibrary.utils.FeedbackSystemViewSetup;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.constants.LibEnums.AUTO_DISPOSE;
import static com.tech.futureteric.feedbacklibrary.constants.LibEnums.BUTTER_KNIFE;
import static com.tech.futureteric.feedbacklibrary.constants.LibEnums.R_X_BINDING;

public class FeedbackSystemBuilder {

    private @LibEnums.Sections
    static int[] mSections;
    private @LibEnums.ViewBinder int mViewBinder;

    public FeedbackSystemBuilder() {}


    public FeedbackSystemBuilder withViewBinder(@LibEnums.ViewBinder int viewBinder){
        mViewBinder = viewBinder;
        return this;
    }

    public FeedbackSystemBuilder withSections(@LibEnums.Sections int[] sections){
        mSections = sections;
        return this;
    }

    public void build(Activity activity){
        // TODO add the rest of viewBinders and remove the init of aClass just declaration.
        Class aClass = null;

        switch (mViewBinder) {
            case BUTTER_KNIFE:
                aClass = FeedbackSystemActivity.class;
                break;

            case AUTO_DISPOSE:

                break;

            case R_X_BINDING:

                break;
        }
        Intent intent = new Intent(activity, aClass);
        intent.putExtras(getFeedbackSystemBundle());
        activity.startActivity(intent);
    }

    public static void buildThenShowDialog(Activity activity){

        MaterialDialog dialog = new MaterialDialog.Builder(activity)
                .title("Feedback")
                .customView(R.layout.activity_feedback_system, true)
                .show();

        // TODO test this function working without crashing
        new FeedbackSystemViewSetup().setupView(activity, getFeedbackSystemBundle(), dialog.getContentView());
    }

    private static Bundle getFeedbackSystemBundle() {
        Bundle bundle = new Bundle();
        // TODO test casting array to parcelable is not crashing
        bundle.putIntArray(BUNDLE_SECTIONS, mSections);
        return bundle;
    }
}
