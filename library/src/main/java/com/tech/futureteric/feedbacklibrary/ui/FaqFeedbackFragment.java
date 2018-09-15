package com.tech.futureteric.feedbacklibrary.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.adapter.FaqAdapter;

import java.util.ArrayList;
import java.util.List;

public class FaqFeedbackFragment extends Fragment {

    private static final String ARG_FAQ_LIST = "faq_list";

    public FaqFeedbackFragment() {}

    public static FaqFeedbackFragment newInstance(List<String> faqList) {
        FaqFeedbackFragment fragment = new FaqFeedbackFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_FAQ_LIST, (ArrayList<String>) faqList);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_feedbacsk, container, false);

        List<String> faqList = null;
        if (getArguments() != null)
            faqList = getArguments().getStringArrayList(ARG_FAQ_LIST);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new FaqAdapter(faqList));

        return view;
    }
}