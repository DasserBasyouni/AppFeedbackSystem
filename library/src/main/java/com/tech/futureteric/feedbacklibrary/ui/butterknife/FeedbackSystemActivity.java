package com.tech.futureteric.feedbacklibrary.ui.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.utils.FeedbackSystemViewSetup;

public class FeedbackSystemActivity extends AppCompatActivity {


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_system);

        new FeedbackSystemViewSetup().setupView(this, getIntent().getExtras(), null);
    }


}