package com.tech.futureteric.feedbacklibrary.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ENABLE_COLORFUL_FEEDBACK;

public class FeedbackSpinnerAdapter extends ArrayAdapter<String> {

    private final List<String> mSections;
    private final boolean mEnableColorfulFeedback;
    @ColorInt private int mPrimaryColor, mSpinnerTextColor;

    public FeedbackSpinnerAdapter(Activity activity, ArrayList<String> sections) {
        super(activity, android.R.layout.simple_spinner_item, sections);
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSections = sections;

        Bundle bundle = activity.getIntent().getExtras();
        assert bundle != null;
        mEnableColorfulFeedback = bundle.getBoolean(BUNDLE_ENABLE_COLORFUL_FEEDBACK);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        if (mEnableColorfulFeedback){
            view.setBackgroundColor(mPrimaryColor);
            ((TextView) view).setTextColor(mSpinnerTextColor);
        }
        return view;
    }

    @Nullable @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return mSections.size();
    }

    public void refreshDropDownStyle(int primaryColor, int spinnerTextColor){
        mPrimaryColor = primaryColor;
        mSpinnerTextColor = spinnerTextColor;
    }
}
