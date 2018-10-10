package com.tech.futureteric.feedbacklibrary.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.futureteric.feedbacklibrary.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder>{

    private List<String> mFaqList;

    public FaqAdapter(List<String> faqList) {
        mFaqList = faqList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_faq_textview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(Html.fromHtml(mFaqList.get(i)));
    }

    @Override
    public int getItemCount() {
        return mFaqList.size();
    }

}
