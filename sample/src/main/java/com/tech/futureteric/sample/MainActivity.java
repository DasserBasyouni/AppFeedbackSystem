package com.tech.futureteric.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tech.futureteric.feedbacklibrary.FeedbackSystemBuilder;
import com.tech.futureteric.feedbacklibrary.Section;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(view -> new FeedbackSystemBuilder()
                .addSection(new Section.FrequentlyAskedQuestions(null))
                .addSection(new Section.FeatureRequest(null))
                .addSection(new Section.GeneralFeedback("dasserbasyouni@gmail.com"))
                .addSection(new Section.BugReport("dasserbasyouni@gmail.com"))
                .addSection(new Section.ContactUs("dasserbasyouni@gmail.com"))
                .buildThenShowDialog(MainActivity.this)
        );
    }
}