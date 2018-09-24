package com.tech.futureteric.feedbacklibrary.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.tech.futureteric.feedbacklibrary.R;

import java.util.Objects;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_USER_UID;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;

public class FeedbackSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_system);

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("");

        //setTheme(R.style.FeedbackLibTheme);

        setupSpinner();
    }

    private void setupSpinner() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                Objects.requireNonNull(bundle.getStringArrayList(BUNDLE_SECTIONS)));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setSelection(bundle.getInt(BUNDLE_CLICKED_SECTION));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sectionName = (((TextView) view).getText().toString());

                Fragment fragment = null;
                if (sectionName.equals(getString(R.string.label_general_feedback)) || sectionName.equals(getString(R.string.label_bug_report))
                        || sectionName.equals(getString(R.string.label_contact_us)))
                    fragment = EmailForumFragment.newInstance(sectionName, getSectionEmail(sectionName));

                else if (sectionName.endsWith(getString(R.string.label_feature_request)))
                    fragment = FeatureRequestFragment.newInstance(getIntent().getExtras()
                            .getInt(BUNDLE_FEATURE_REQUEST_USER_UID));

                if (fragment != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                        fragment).commit();
            }

            private String getSectionEmail(String sectionName) {
                String email = null;

                if (sectionName.equals(getString(R.string.label_general_feedback)))
                    email = getIntent().getExtras().getString(BUNDLE_GENERAL_FEEDBACK_EMAIL);
                else if (sectionName.equals(getString(R.string.label_bug_report)))
                    email = getIntent().getExtras().getString(BUNDLE_BUG_REPORT_EMAIL);
                else if (sectionName.equals(getString(R.string.label_contact_us)))
                    email = getIntent().getExtras().getString(BUNDLE_CONTACT_US_EMAIL);

                return email;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}