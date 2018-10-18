package com.tech.futureteric.feedbacklibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.model.Forum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeatureRequestAdapter extends RecyclerView.Adapter<FeatureRequestAdapter.ViewHolder>{

    private List<Forum> mRequests;

    public FeatureRequestAdapter(List<Forum> requests) {
        mRequests = requests;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView featureTitle, featureDescription, votesCount;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            featureTitle =  itemView.findViewById(R.id.textView_featureTitle);
            featureDescription =  itemView.findViewById(R.id.textView_featureDescription);
            votesCount =  itemView.findViewById(R.id.textView_votesCount);
            //voteUp =  itemView.findViewById(R.id.imageButton_voteUp);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_feature_request, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Forum currentFeature = mRequests.get(i);

        viewHolder.featureTitle.setText(currentFeature.getTitle());
        viewHolder.featureDescription.setText(currentFeature.getDescription());
        viewHolder.votesCount.setText(String.valueOf(currentFeature.getVoteCount()));
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

}
