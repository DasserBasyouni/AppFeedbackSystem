package com.tech.futureteric.feedbacklibrary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tech.futureteric.feedbacklibrary.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_USER_UID;
import static com.tech.futureteric.feedbacklibrary.database.FireStoreUtils.listenToFeatureRequestListAndUpdateRV;

public class FeatureRequestFragment extends Fragment {

    private View rootView;

    public FeatureRequestFragment() {}

    public static FeatureRequestFragment newInstance(int userUid) {
        FeatureRequestFragment fragment = new FeatureRequestFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_USER_UID, userUid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirebaseFirestore firestore) {
        EventBus.getDefault().unregister(this);
        listenToFeatureRequestListAndUpdateRV(getActivity(), firestore, rootView);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_feature_request, container, false);

        if (getArguments() != null) {
            rootView.findViewById(R.id.fab_createFeatureRequest).setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), FirestoreForumActivity.class);
                intent.putExtras(getArguments());
                startActivity(intent);
            });
        } else
            throw new NullPointerException("Bundle is equal to null");

        return rootView;
    }
}