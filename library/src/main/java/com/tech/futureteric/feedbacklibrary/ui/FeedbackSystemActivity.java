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

import java.util.ArrayList;
import java.util.List;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_RECEIVER_EMAIL;
import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;

public class FeedbackSystemActivity extends AppCompatActivity {

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getSpinnerList(bundle));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setSelection(Integer.parseInt(bundle.getString(BUNDLE_CLICKED_SECTION)));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sectionName = (((TextView) view).getText().toString());
                String email = getIntent().getExtras().getString(BUNDLE_RECEIVER_EMAIL);

                Fragment fragment = null;
                if (sectionName.equals(getString(R.string.label_bug_report))
                        || sectionName.equals(getString(R.string.label_general_feedback))
                        || sectionName.equals(getString(R.string.label_contact_us))){
                    fragment = SimilarFeedbackFragment.newInstance(sectionName, email);

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                        fragment).commit();}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private List<String> getSpinnerList(Bundle bundle) {
        int[] sections = bundle.getIntArray(BUNDLE_SECTIONS);

        List<String> list = new ArrayList<>();

        assert sections != null;
        for (int section : sections) {
            String sectionType = null;
            switch (section) {
                case 0:
                    sectionType = getString(R.string.label_frequently_asked_questions);
                    break;
                case 1:
                    sectionType = getString(R.string.label_feature_request);
                    break;
                case 2:
                    sectionType = getString(R.string.label_general_feedback);
                    break;
                case 3:
                    sectionType = getString(R.string.label_bug_report);
                    break;
                case 4:
                    sectionType = getString(R.string.label_contact_us);
                    break;
                case 5:
                    sectionType = "ALL";
                    break;
            }

            assert sectionType != null;
            if (!sectionType.equals("ALL"))
                list.add(sectionType);
            else {
                return new ArrayList<String>() {{
                    add("Frequently Asked Questions");
                    add("Feature Request");
                    add("General Feedback");
                    add("Contact Us");
                    add("Bug Report");
                }};
            }
        }

        return list;
    }

}