package com.tech.futureteric.feedbacklibrary.ui;

import android.os.Bundle;

import com.tech.futureteric.feedbacklibrary.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_USER_UID;

public class FirestoreForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore_forum);

        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout_firestoreForum, FirestoreForumFragment
                        .newInstance(Objects.requireNonNull(getIntent().getExtras()).getString(BUNDLE_USER_UID)))
                .commit();
    }
}
