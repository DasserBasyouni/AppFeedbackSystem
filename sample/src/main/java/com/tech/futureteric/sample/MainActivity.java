package com.tech.futureteric.sample;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tech.futureteric.feedbacklibrary.Section;
import com.tech.futureteric.feedbacklibrary.builder.FeedbackSystemBuilder;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        List<String> test = new ArrayList<>();
        test.add("testsss");

        button.setOnClickListener(view -> new FeedbackSystemBuilder()
                .addSection(new Section.FrequentlyAskedQuestions(test))
                .addSection(new Section.FeatureRequest(FirebaseFirestore.getInstance(),12345))
                .addSection(new Section.GeneralFeedback("dasserbasyouni@gmail.com"))
                .addSection(new Section.BugReport("dasserbasyouni@gmail.com"))
                .addSection(new Section.ContactUs("dasserbasyouni@gmail.com"))
                .dialogWithTitleAndSentence("Feedback", "We care about you")
                .enableColoredStatusBar()
                .enableColorfulFeedback(true)
                .buildThenShowDialog(MainActivity.this)
        );
    }

}