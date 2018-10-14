package com.tech.futureteric.feedbacklibrary.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.adapter.FeedbackSpinnerAdapter;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_BUG_REPORT_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CONTACT_US_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_ENABLE_COLORED_STATUS;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_LIST;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FAQ_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_FEATURE_REQUEST_USER_UID;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_GENERAL_FEEDBACK_THEME;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;
import static com.tech.futureteric.feedbacklibrary.utils.AnimUtils.changeToolAndStatusBarsColorWithAnimation;
import static com.tech.futureteric.feedbacklibrary.utils.ThemeUtils.getAccentColor;
import static com.tech.futureteric.feedbacklibrary.utils.ThemeUtils.getPrimaryColor;
import static com.tech.futureteric.feedbacklibrary.utils.ThemeUtils.getPrimaryDarkColor;
import static com.tech.futureteric.feedbacklibrary.utils.ThemeUtils.getSpinnerTextColor;

public class FeedbackSystemActivity extends AppCompatActivity {

    private boolean mSpinnerInitialized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_system);

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("");

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
                String sectionName = (((TextView) view).getText().toString()), key = "";
                Fragment fragment = null;
                int themeResId = 0;

                if (sectionName.equals(getString(R.string.label_frequently_asked_questions))) {
                    key = BUNDLE_FAQ_THEME;
                    themeResId = bundle.getInt(key, R.style.FaqSectionTheme);
                    fragment = EmailForumFragment.newInstance(sectionName, getSectionEmail(sectionName),
                            getPrimaryColor(FeedbackSystemActivity.this, key, themeResId, bundle),
                            getAccentColor(FeedbackSystemActivity.this, key, themeResId, bundle)
                            , themeResId);

                } else if (sectionName.endsWith(getString(R.string.label_feature_request))) {
                    key = BUNDLE_FEATURE_REQUEST_THEME;
                    themeResId = bundle.getInt(key, R.style.FeatureRequestSectionTheme);
                    fragment = FeatureRequestFragment.newInstance(getIntent().getExtras()
                            .getInt(BUNDLE_FEATURE_REQUEST_USER_UID),
                            getPrimaryColor(FeedbackSystemActivity.this, key, themeResId, bundle),
                            getAccentColor(FeedbackSystemActivity.this, key, themeResId, bundle));

                } else if (sectionName.equals(getString(R.string.label_general_feedback))) {
                    key = BUNDLE_GENERAL_FEEDBACK_THEME;
                    themeResId = bundle.getInt(key, R.style.GeneralFeedbackSectionTheme);
                    fragment = FaqFragment.newInstance(getIntent().getExtras().getStringArrayList(BUNDLE_FAQ_LIST),
                            getPrimaryColor(FeedbackSystemActivity.this, key, themeResId, bundle),
                            getAccentColor(FeedbackSystemActivity.this, key, themeResId, bundle));

                } else if (sectionName.equals(getString(R.string.label_bug_report))) {
                    key = BUNDLE_BUG_REPORT_THEME;
                    themeResId = bundle.getInt(key, R.style.BugReportSectionTheme);
                    fragment = EmailForumFragment.newInstance(sectionName, getSectionEmail(sectionName),
                            getPrimaryColor(FeedbackSystemActivity.this, key, themeResId, bundle),
                            getAccentColor(FeedbackSystemActivity.this, key, themeResId, bundle)
                            , themeResId);

                } else if (sectionName.equals(getString(R.string.label_contact_us))) {
                    key = BUNDLE_CONTACT_US_THEME;
                    themeResId = bundle.getInt(key, R.style.ContactUsSectionTheme);
                    fragment = EmailForumFragment.newInstance(sectionName, getSectionEmail(sectionName),
                            getPrimaryColor(FeedbackSystemActivity.this, key, themeResId, bundle),
                            getAccentColor(FeedbackSystemActivity.this, key, themeResId, bundle)
                            , themeResId);
                }

                refreshActivityTheme(themeResId, key, adapterView);
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }

            private void refreshActivityTheme(int theme, String key, AdapterView<?> adapterView) {
                int primaryColor = getPrimaryColor(FeedbackSystemActivity.this, key, theme, bundle);
                int spinnerTextColor = getSpinnerTextColor(FeedbackSystemActivity.this, key, theme, bundle);

                changeToolAndStatusBarsColorWithAnimation(
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                                && Objects.requireNonNull(getIntent().getExtras())
                                .getBoolean(BUNDLE_ENABLE_COLORED_STATUS, false) ?
                                getWindow().getStatusBarColor():0,
                        ((ColorDrawable) findViewById(R.id.toolbar).getBackground()).getColor(),
                        getPrimaryDarkColor(FeedbackSystemActivity.this, key, theme, bundle),
                        primaryColor, FeedbackSystemActivity.this, mSpinnerInitialized);

                if (!mSpinnerInitialized) mSpinnerInitialized = true;

               ((FeedbackSpinnerAdapter) spinner.getAdapter())
                       .refreshDropDownStyle(primaryColor, spinnerTextColor);
                ((TextView) adapterView.getSelectedView()).setTextColor(
                        getSpinnerTextColor(FeedbackSystemActivity.this, key, theme, bundle));
                spinner.getBackground().setColorFilter(spinnerTextColor, PorterDuff.Mode.SRC_ATOP);
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
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

}