package com.tech.futureteric.feedbacklibrary.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.tech.futureteric.feedbacklibrary.customView.ThemeableTextInputLayout;
import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ACCENT_COLOR;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_PRIMARY_COLOR;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTION_RES_ID_THEME;
import static com.tech.futureteric.feedbacklibrary.utils.Utils.getApplicationName;
import static com.tech.futureteric.feedbacklibrary.utils.ValidationUtils.isEditTextInputValid;


public class EmailForumFragment extends Fragment {

    private static final String ARG_SECTION_NAME = "section_name";
    private static final String ARG_RECEIVER_EMAIL = "receiver_email";

    public EmailForumFragment() {}

    static EmailForumFragment newInstance(String sectionName, String receiverEmail,
                                          int colorPrimary, int colorAccent, int theme) {
        EmailForumFragment fragment = new EmailForumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, sectionName);
        args.putString(ARG_RECEIVER_EMAIL, receiverEmail);
        args.putInt(BUNDLE_PRIMARY_COLOR, colorPrimary);
        args.putInt(BUNDLE_ACCENT_COLOR, colorAccent);
        args.putInt(BUNDLE_SECTION_RES_ID_THEME, theme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ThemeableTextInputLayout.setTheme(getArguments().getInt(BUNDLE_SECTION_RES_ID_THEME));
        View rootView = inflater.inflate(R.layout.fragment_email_forum, container, false);

        String sectionName = null, receiverEmail = null;
        if (getArguments() != null){
            sectionName = getArguments().getString(ARG_SECTION_NAME);
            receiverEmail = getArguments().getString(ARG_RECEIVER_EMAIL);
        }

        setupView(rootView, sectionName, receiverEmail);
        return rootView;
    }

    private void setupView(View rootView, String sectionName, String receiverEmail) {
        TextInputLayout subject_til = rootView.findViewById(R.id.textInputLayout_subject);
        subject_til.getEditText().setHighlightColor(Color.YELLOW);
        subject_til.getEditText().setHintTextColor(Color.GREEN);

        FloatingActionButton fab = rootView.findViewById(R.id.fab_sendForum);
        fab.setBackgroundTintList(ColorStateList.valueOf(
                Objects.requireNonNull(getArguments()).getInt(BUNDLE_ACCENT_COLOR)));

        fab.setOnClickListener(view1 -> {
            EditText subject_et = subject_til.getEditText();
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