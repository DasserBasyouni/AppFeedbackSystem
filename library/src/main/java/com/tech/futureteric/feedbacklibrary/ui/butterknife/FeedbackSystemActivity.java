package com.tech.futureteric.feedbacklibrary.ui.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackSystemActivity extends AppCompatActivity {

    @BindView(R2.id.toolbar) Toolbar toolbar;
    @BindView(R2.id.spinner) Spinner spinner;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_system);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupSpinner();
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }


}