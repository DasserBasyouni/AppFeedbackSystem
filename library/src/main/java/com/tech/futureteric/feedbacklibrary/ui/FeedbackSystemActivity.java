package com.tech.futureteric.feedbacklibrary.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.adapter.FeedbackSpinnerAdapter;

import java.util.Objects;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CUSTOM_COLORS_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_USER_UID;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTION_COLOR;
import static com.tech.futureteric.feedbacklibrary.utils.UiUtils.getSectionColorId;

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

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new FeedbackSpinnerAdapter(this,
                Objects.requireNonNull(bundle.getStringArrayList(BUNDLE_SECTIONS))));
        spinner.setSelection(bundle.getInt(BUNDLE_CLICKED_SECTION));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sectionName = (((TextView) view).getText().toString());
                Fragment fragment = null;

                if (sectionName.equals(getString(R.string.label_frequently_asked_questions)))
                    fragment = EmailForumFragment.newInstance(sectionName, getSectionEmail(sectionName));

                else if (sectionName.equals(getString(R.string.label_general_feedback)))
                    fragment = FaqFragment.newInstance(getIntent().getExtras().getStringArrayList(BUNDLE_FAQ_LIST));

                else if (sectionName.equals(getString(R.string.label_bug_report)))
                    fragment = EmailForumFragment.newInstance(sectionName, getSectionEmail(sectionName));

                else if (sectionName.equals(getString(R.string.label_contact_us)))
                    fragment = EmailForumFragment.newInstance(sectionName, getSectionEmail(sectionName));

                else if (sectionName.endsWith(getString(R.string.label_feature_request)))
                    fragment = FeatureRequestFragment.newInstance(getIntent().getExtras()
                            .getInt(BUNDLE_FEATURE_REQUEST_USER_UID));

                int sectionColor = getSectionColorId(view.getContext(), sectionName,
                        bundle.getIntegerArrayList(BUNDLE_CUSTOM_COLORS_LIST), i);
                bundle.putInt(BUNDLE_SECTION_COLOR, sectionColor);
                ((FeedbackSpinnerAdapter) spinner.getAdapter()).refreshDropDownViewBackground(sectionColor);

                if (fragment != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                        fragment).commit();

                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(
                        new ColorDrawable(getResources().getColor(bundle.getInt(BUNDLE_SECTION_COLOR))));
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