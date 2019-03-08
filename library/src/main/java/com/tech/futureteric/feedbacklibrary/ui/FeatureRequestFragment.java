package com.tech.futureteric.feedbacklibrary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tech.futureteric.feedbacklibrary.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ACCENT_COLOR;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_PRIMARY_COLOR;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTION_RES_ID_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_USER_UID;
import static com.tech.futureteric.feedbacklibrary.database.FireStoreUtils.listenToFeatureRequestListAndUpdateRV;

public class FeatureRequestFragment extends Fragment {

    private View rootView;

    public FeatureRequestFragment() {}

    static FeatureRequestFragment newInstance(int userUid, int colorPrimary, int colorAccent, int theme) {
        FeatureRequestFragment fragment = new FeatureRequestFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_USER_UID, userUid);
        args.putInt(BUNDLE_PRIMARY_COLOR, colorPrimary);
        args.putInt(BUNDLE_ACCENT_COLOR, colorAccent);
        args.putInt(BUNDLE_SECTION_RES_ID_THEME, theme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_feature_request, container, false);

        setProgressBarColorToAccentColor();

        if (getArguments() != null) {
            rootView.findViewById(R.id.fab_createFeatureRequest).setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), CreateFeatureRequestActivity.class);
                intent.putExtras(getArguments());
                startActivity(intent);
            });
        } else
            throw new IllegalArgumentException("Bundle is equal to null");

        return rootView;
    }

    private void setProgressBarColorToAccentColor() {
        ((ProgressBar) rootView.findViewById(R.id.progressBar_featureRequest)).getIndeterminateDrawable().setColorFilter(
                Objects.requireNonNull(getArguments()).getInt(BUNDLE_ACCENT_COLOR), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirebaseFirestore firestore) {
        EventBus.getDefault().unregister(this);
        listenToFeatureRequestListAndUpdateRV(getActivity(), firestore, rootView);
    }
}