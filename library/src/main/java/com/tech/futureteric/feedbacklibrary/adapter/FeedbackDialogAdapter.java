package com.tech.futureteric.feedbacklibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CUSTOM_COLORS_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ENABLE_COLORFUL_FEEDBACK;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_THEME;
import static com.tech.futureteric.feedbacklibrary.utils.ThemeUtils.getPrimaryColor;

public class FeedbackDialogAdapter extends RecyclerView.Adapter<FeedbackDialogAdapter.ViewHolder>{

    private List<String> mFeedbackSections;
    private Bundle mBundle;
    private final Activity mActivity;

    public FeedbackDialogAdapter(Activity activity, List<String> feedbackSections, Bundle bundle) {
        mFeedbackSections = feedbackSections;
        mBundle = bundle;
        mActivity = activity;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MaterialButton button;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = (MaterialButton) itemView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_feedback_dialog_button, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String sectionName = mFeedbackSections.get(i);

        if (mBundle.getBoolean(BUNDLE_ENABLE_COLORFUL_FEEDBACK))
            enableColorfulButtons(sectionName, mBundle.getIntegerArrayList(BUNDLE_CUSTOM_COLORS_LIST)
                    , viewHolder, i);

        viewHolder.button.setText(sectionName);
        viewHolder.button.setOnClickListener(view -> {
            mBundle.putInt(BUNDLE_CLICKED_SECTION, i);
            Intent intent = new Intent(view.getContext(), FeedbackSystemActivity.class);
            intent.putExtras(mBundle);
            view.getContext().startActivity(intent);
            mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    public int getItemCount() {
        return mFeedbackSections.size();
    }

    private void enableColorfulButtons(String sectionName, ArrayList<Integer> customColorsList,
                                       ViewHolder viewHolder, int currentPosition) {
        Context context = viewHolder.itemView.getContext();

        if (customColorsList == null) {
            if (sectionName.equals(context.getString(R.string.label_frequently_asked_questions)))
                viewHolder.button.setBackgroundTintList(ColorStateList.valueOf(
                        getPrimaryColor(viewHolder.itemView.getContext(), BUNDLE_FAQ_THEME,
                                R.style.FaqSectionTheme, mBundle)));

            else if (sectionName.equals(context.getString(R.string.label_feature_request)))
                viewHolder.button.setBackgroundTintList(ColorStateList.valueOf(
                        getPrimaryColor(viewHolder.itemView.getContext(), BUNDLE_FEATURE_REQUEST_THEME,
                                R.style.FeatureRequestSectionTheme, mBundle)));

            else if (sectionName.equals(context.getString(R.string.label_general_feedback)))
                viewHolder.button.setBackgroundTintList(ColorStateList.valueOf(
                        getPrimaryColor(viewHolder.itemView.getContext(), BUNDLE_GENERAL_FEEDBACK_THEME,
                                R.style.GeneralFeedbackSectionTheme, mBundle)));

            else if (sectionName.equals(context.getString(R.string.label_bug_report)))
                viewHolder.button.setBackgroundTintList(ColorStateList.valueOf(
                        getPrimaryColor(viewHolder.itemView.getContext(), BUNDLE_BUG_REPORT_THEME,
                                R.style.BugReportSectionTheme, mBundle)));

            else if (sectionName.equals(context.getString(R.string.label_contact_us)))
                viewHolder.button.setBackgroundTintList(ColorStateList.valueOf(
                        getPrimaryColor(viewHolder.itemView.getContext(), BUNDLE_CONTACT_US_THEME,
                                R.style.ContactUsSectionTheme, mBundle)));

        } else {
            if (currentPosition <= customColorsList.size())
                viewHolder.button.setBackgroundTintList(ContextCompat
                        .getColorStateList(context, customColorsList.get(currentPosition)));
            else
                throw new NullPointerException("You must specify all buttons colors of used sections withing using setCustomColorfulFeedback(List<Integer>) - missing color for section button number "
                        + currentPosition+1);
        }

    }


}
