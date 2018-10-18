package com.tech.futureteric.feedbacklibrary.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;
import com.tech.futureteric.feedbacklibrary.model.Forum;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_USER_UID;
import static com.tech.futureteric.feedbacklibrary.database.FireStoreUtils.addFeatureRequest;
import static com.tech.futureteric.feedbacklibrary.utils.ValidationUtils.isEditTextInputValid;


public class FirestoreForumFragment extends Fragment {

    private static final String ARG_SECTION_NAME = "section_name";
    private View rootView;

    public FirestoreForumFragment() {}


    static FirestoreForumFragment newInstance(String sectionName) {
        FirestoreForumFragment fragment = new FirestoreForumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, sectionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_email_forum, container, false);
        setupView();
        return rootView;
    }

    private void setupView() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirebaseFirestore db) {
        EventBus.getDefault().unregister(this);
        FloatingActionButton send_fab = rootView.findViewById(R.id.fab_sendForum);

        send_fab.setImageDrawable(new IconicsDrawable(getContext())
                .icon(GoogleMaterial.Icon.gmd_done)
                .color(Color.WHITE)
                .sizeDp(24));
        send_fab.setOnClickListener(v -> {
            EditText title = ((TextInputLayout)rootView.findViewById(R.id.textInputLayout_subject)).getEditText();
            EditText description = ((TextInputLayout)rootView.findViewById(R.id.textInputLayout_description)).getEditText();

            // TODO refactor addFeatureRequest() if there is more than one use of this fragment
            assert title != null;
            assert description != null;
            if (isEditTextInputValid(title, LibEnums.TEXT)
                    && isEditTextInputValid(description, LibEnums.TEXT))
                addFeatureRequest(db, new Forum(Objects.requireNonNull(
                        getArguments()).getString(BUNDLE_USER_UID), title.getText().toString()
                        ,description.getText().toString()));
        });
    }

}