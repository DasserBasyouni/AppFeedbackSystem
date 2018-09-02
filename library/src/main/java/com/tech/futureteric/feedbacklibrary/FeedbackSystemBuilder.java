package com.tech.futureteric.feedbacklibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.tech.futureteric.feedbacklibrary.constants.Sections;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;

public class FeedbackSystemBuilder {

    private Sections[] mSections;


    public FeedbackSystemBuilder() {}


    public FeedbackSystemBuilder withSections(Sections... sections){
        mSections = sections;
        return this;
    }

    public void build(Activity activity){
        Intent intent = new Intent(activity, FeedbackSystemActivity.class);
        intent.putExtras(getFeedbackSystemBundle());
        activity.startActivity(intent);
    }

    private Bundle getFeedbackSystemBundle() {
        Bundle bundle = new Bundle();
        // TODO test casting array to parcelable is not crashing
        bundle.putParcelableArray(BUNDLE_SECTIONS, (Parcelable[]) mSections);
        return bundle;
    }
}
