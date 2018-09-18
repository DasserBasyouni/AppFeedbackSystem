package com.tech.futureteric.feedbacklibrary.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.ui.FeedbackSystemActivity;

import java.util.List;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;

public class FeedbackDialogAdapter extends RecyclerView.Adapter<FeedbackDialogAdapter.ViewHolder>{

    private List<String> mFeedbackSections;
    private Bundle mBundle;

    public FeedbackDialogAdapter(List<String> feedbackSections, Bundle bundle) {
        mFeedbackSections = feedbackSections;
        mBundle = bundle;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = (Button) itemView;
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
        viewHolder.button.setText(mFeedbackSections.get(i));
        viewHolder.button.setOnClickListener(view -> {
            mBundle.putInt(BUNDLE_CLICKED_SECTION, i);

            Intent intent = new Intent(view.getContext(), FeedbackSystemActivity.class);
            intent.putExtras(mBundle);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mFeedbackSections.size();
    }

}
