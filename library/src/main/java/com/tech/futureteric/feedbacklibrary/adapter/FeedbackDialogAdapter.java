package com.tech.futureteric.feedbacklibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import java.util.ArrayList;
import java.util.List;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ENABLE_COLORFUL_FEEDBACK;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CUSTOM_COLORS_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTION_COLOR;
import static com.tech.futureteric.feedbacklibrary.utils.UiUtils.getSectionColorId;

public class FeedbackDialogAdapter extends RecyclerView.Adapter<FeedbackDialogAdapter.ViewHolder>{

    private List<String> mFeedbackSections;
    private Bundle mBundle;

    public FeedbackDialogAdapter(List<String> feedbackSections, Bundle bundle) {
        mFeedbackSections = feedbackSections;
        mBundle = bundle;
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
            mBundle.putInt(BUNDLE_SECTION_COLOR, getSectionColorId(view.getContext(), sectionName,
                    mBundle.getIntegerArrayList(BUNDLE_CUSTOM_COLORS_LIST), i));

            Intent intent = new Intent(view.getContext(), FeedbackSystemActivity.class);
            intent.putExtras(mBundle);
            view.getContext().startActivity(intent);
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
                viewHolder.button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.faqBtn_900));
            else if (sectionName.equals(context.getString(R.string.label_feature_request)))
                viewHolder.button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.featureRequestBtn_A700));
            else if (sectionName.equals(context.getString(R.string.label_general_feedback)))
                viewHolder.button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.generalFeedbackBtn_900));
            else if (sectionName.equals(context.getString(R.string.label_bug_report)))
                viewHolder.button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.bugReportBtn_A700));
            else if (sectionName.equals(context.getString(R.string.label_contact_us)))
                viewHolder.button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.contactUsBtn_900));

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
