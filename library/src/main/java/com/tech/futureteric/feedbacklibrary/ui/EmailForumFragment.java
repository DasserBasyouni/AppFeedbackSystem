package com.tech.futureteric.feedbacklibrary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;

import java.util.Objects;

import static com.tech.futureteric.feedbacklibrary.utils.Utils.getApplicationName;
import static com.tech.futureteric.feedbacklibrary.utils.ValidationUtils.isEditTextInputValid;


public class EmailForumFragment extends Fragment {

    private static final String ARG_SECTION_NAME = "section_name";
    private static final String ARG_RECEIVER_EMAIL = "receiver_email";

    public EmailForumFragment() {}

    public static EmailForumFragment newInstance(String sectionName, String receiverEmail) {
        EmailForumFragment fragment = new EmailForumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, sectionName);
        args.putString(ARG_RECEIVER_EMAIL, receiverEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_email_forum, container, false);

        String sectionName = null, receiverEmail = null;
        if (getArguments() != null){
            sectionName = getArguments().getString(ARG_SECTION_NAME);
            receiverEmail = getArguments().getString(ARG_RECEIVER_EMAIL);
        }

        setupView(rootView, sectionName, receiverEmail);

        return rootView;
    }

    public void setupView(View rootView, String sectionName, String receiverEmail) {
        rootView.findViewById(R.id.fab_sendForum).setOnClickListener(view1 -> {

            EditText subject_et = ((TextInputLayout) rootView.findViewById(
                    R.id.textInputLayout_subject)).getEditText();
            EditText description_et = ((TextInputLayout) rootView.findViewById(
                    R.id.textInputLayout_description)).getEditText();

            assert subject_et != null; assert description_et != null;
            if (isEditTextInputValid(subject_et, LibEnums.TEXT)
                    && isEditTextInputValid(description_et, LibEnums.TEXT))
                sendForumToEmail(sectionName, subject_et.getText(), description_et.getText(), receiverEmail);

        });
    }

    private void sendForumToEmail(String sectionName, Editable subject, Editable description, String receiverEmail1) {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] {receiverEmail1});
        Email.putExtra(Intent.EXTRA_SUBJECT, getApplicationName(Objects.requireNonNull(
                getContext())) + " " + sectionName + ": " + subject);
        Email.putExtra(Intent.EXTRA_TEXT, description + "\n\nSent by Feedback System <3");
        startActivity(Intent.createChooser(Email, "Send Feedback test:"));
    }

}