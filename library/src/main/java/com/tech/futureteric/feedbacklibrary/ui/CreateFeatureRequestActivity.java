package com.tech.futureteric.feedbacklibrary.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.tech.futureteric.feedbacklibrary.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTION_RES_ID_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_USER_UID;

public class CreateFeatureRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(Objects.requireNonNull(getIntent().getExtras()).getInt(BUNDLE_SECTION_RES_ID_THEME));
        super.onCreate(savedInstanceState);
        // TODO search for just a framlayuot layout in andorid.R.
        setContentView(R.layout.activity_firestore_forum);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout_firestoreForum, CreateFeatureRequestFragment
                        .newInstance(Objects.requireNonNull(getIntent().getExtras()).getString(BUNDLE_USER_UID)))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
