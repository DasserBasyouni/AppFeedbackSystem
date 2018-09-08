package com.tech.futureteric.feedbacklibrary.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tech.futureteric.feedbacklibrary.R;

import java.util.ArrayList;
import java.util.List;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_CLICKED_SECTION;
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
                // TODO launch fragments of feedback system
                Toast.makeText(FeedbackSystemActivity.this, "items is selected", Toast.LENGTH_SHORT).show();
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
            String sectionName = null;
            switch (section) {
                case 0:
                    sectionName = "Frequently Asked Questions";
                    break;
                case 1:
                    sectionName = "Feature Request";
                    break;
                case 2:
                    sectionName = "General Feedback";
                    break;
                case 3:
                    sectionName = "Contact Us";
                    break;
                case 4:
                    sectionName = "Bug Report";
                    break;
                case 5:
                    sectionName = "ALL";
                    break;
            }

            assert sectionName != null;
            if (!sectionName.equals("ALL"))
                list.add(sectionName);
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