package com.tech.futureteric.feedbacklibrary.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tech.futureteric.feedbacklibrary.R;

import java.util.Objects;

// Similar feedback fragments are: general feedback, bug report & contact us
public class SimilarFeedbackFragment extends Fragment {

    private static final String ARG_SECTION_NAME = "section_name";
    private static final String ARG_RECEIVER_EMAIL = "receiver_email";

    public SimilarFeedbackFragment() {}

    public static SimilarFeedbackFragment newInstance(String sectionName, String receiverEmail) {
        SimilarFeedbackFragment fragment = new SimilarFeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, sectionName);
        args.putString(ARG_RECEIVER_EMAIL, receiverEmail);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_similar_feedbacsk, container, false);

        String sectionName = null, receiverEmail = null;
        if (getArguments() != null){
            sectionName = getArguments().getString(ARG_SECTION_NAME);
            receiverEmail = getArguments().getString(ARG_RECEIVER_EMAIL);
        }

        String finalReceiverEmail = receiverEmail;
        String finalSectionName = sectionName;
        view.findViewById(R.id.floatingActionButton).setOnClickListener(view1 -> {

            EditText subject_et = ((TextInputLayout) view.findViewById(
                    R.id.textInputLayout_subject)).getEditText();
            EditText description_et = ((TextInputLayout) view.findViewById(
                    R.id.textInputLayout_description)).getEditText();

            assert subject_et != null; assert description_et != null;
            if (validateEmailInputs(subject_et, description_et))
                sendFeedbackEmail(finalSectionName, subject_et.getText(), description_et.getText(), finalReceiverEmail);

        });

        return view;
    }

    private boolean validateEmailInputs(EditText subject_et, EditText description_et) {
        boolean valid = true;

        if (TextUtils.isEmpty(subject_et.getText())){
            subject_et.setError("Subject section is required");
            valid = false;
        } else
            subject_et.setError(null);

        if (TextUtils.isEmpty(description_et.getText())){
            description_et.setError("Description section is required");
            valid = false;
        } else
            description_et.setError(null);

        return valid;
    }

    private void sendFeedbackEmail(String sectionName, Editable subject, Editable description, String receiverEmail1) {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] {receiverEmail1});
        Email.putExtra(Intent.EXTRA_SUBJECT, getApplicationName(Objects.requireNonNull(
                getContext())) + " " + sectionName + ": " + subject);
        Email.putExtra(Intent.EXTRA_TEXT, description + "\n\nSent by Feedback System <3");
        startActivity(Intent.createChooser(Email, "Send Feedback test:"));
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
}