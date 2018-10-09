package com.tech.futureteric.feedbacklibrary.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.adapter.FeedbackDialogAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FeedbackDialogFragment extends DialogFragment {

    private static final String ARG_SECTIONS = "arg_sections";
    private static final String ARG_DIALOG_TITLE = "dialog_title";
    private static final String ARG_DIALOG_SENTENCE = "dialog_sentence";

    public static FeedbackDialogFragment newInstance(String dialogTitle, String dialogSentence, List<String> sections, Bundle bundle){
        FeedbackDialogFragment fragment = new FeedbackDialogFragment();
        bundle.putStringArrayList(ARG_SECTIONS, (ArrayList<String>) sections);
        bundle.putString(ARG_DIALOG_TITLE, dialogTitle);
        bundle.putString(ARG_DIALOG_SENTENCE, dialogSentence);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_dialog, container, false);

        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);

        ((TextView)view.findViewById(R.id.textView_dialogTitle))
                .setText(Objects.requireNonNull(getArguments()).getString(ARG_DIALOG_TITLE));

        String sentence = getArguments().getString(ARG_DIALOG_SENTENCE);
        TextView sentence_tv = view.findViewById(R.id.textView_dialogSentence);
        if (TextUtils.isEmpty(sentence))
            sentence_tv.setVisibility(View.GONE);
        else
            sentence_tv.setText(sentence);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_dialogList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        assert getArguments() != null;
        recyclerView.setAdapter(new FeedbackDialogAdapter(
                        Objects.requireNonNull(getArguments().getStringArrayList(ARG_SECTIONS))
                        ,getArguments()));

        return view;
    }
}
