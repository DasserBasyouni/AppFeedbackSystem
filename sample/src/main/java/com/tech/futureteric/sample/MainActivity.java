package com.tech.futureteric.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tech.futureteric.feedbacklibrary.builder.FeedbackSystemBuilder;
import com.tech.futureteric.feedbacklibrary.Section;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> test = new ArrayList<>();
        test.add("testsss");

        button.setOnClickListener(view -> new FeedbackSystemBuilder()
                .addSection(new Section.FrequentlyAskedQuestions(test))
                .addSection(new Section.FeatureRequest( FirebaseFirestore.getInstance(),12345))
                .addSection(new Section.GeneralFeedback("dasserbasyouni@gmail.com"))
                .addSection(new Section.BugReport("dasserbasyouni@gmail.com"))
                .addSection(new Section.ContactUs("dasserbasyouni@gmail.com"))
                .dialogWithTitleAndSentence("Feedback", "We care about you")
                .enableColorfulFeedback(true)
                .buildThenShowDialog(MainActivity.this)
        );
    }

}