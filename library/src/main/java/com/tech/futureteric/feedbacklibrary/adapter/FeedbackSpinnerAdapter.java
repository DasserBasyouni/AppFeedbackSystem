package com.tech.futureteric.feedbacklibrary.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ENABLE_COLORFUL_FEEDBACK;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTION_COLOR;

public class FeedbackSpinnerAdapter extends ArrayAdapter<String> {

    private final List<String> mSections;
    private final boolean mEnableColorfulFeedback;
    private int mColorId;

    public FeedbackSpinnerAdapter(Activity activity, ArrayList<String> sections) {
        super(activity, android.R.layout.simple_spinner_item, sections);
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSections = sections;

        Bundle bundle = activity.getIntent().getExtras();
        assert bundle != null;
        mEnableColorfulFeedback = bundle.getBoolean(BUNDLE_ENABLE_COLORFUL_FEEDBACK);
        mColorId = Objects.requireNonNull(activity.getIntent().getExtras()).getInt(BUNDLE_SECTION_COLOR);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,  @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        if (mEnableColorfulFeedback)
            view.setBackgroundColor(view.getContext().getResources().getColor(mColorId));
        return view;
    }

    public void refreshDropDownViewBackground(int colorId){
        mColorId = colorId;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return mSections.size();
    }

}
