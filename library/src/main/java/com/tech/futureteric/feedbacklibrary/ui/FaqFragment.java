package com.tech.futureteric.feedbacklibrary.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.adapter.FaqAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ACCENT_COLOR;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_PRIMARY_COLOR;

public class FaqFragment extends Fragment {

    private static final String ARG_FAQ_LIST = "faq_list";

    static FaqFragment newInstance(List<String> faqList, int colorPrimary, int colorAccent) {
        FaqFragment fragment = new FaqFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_FAQ_LIST, (ArrayList<String>) faqList);
        args.putInt(BUNDLE_PRIMARY_COLOR, colorPrimary);
        args.putInt(BUNDLE_ACCENT_COLOR, colorAccent);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

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