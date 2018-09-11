package com.tech.futureteric.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tech.futureteric.feedbacklibrary.FeedbackSystemBuilder;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;

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
                .withSections(new int[]{LibEnums.ALL})
                .setReceiverEmail("dasserbasyouni@gmail.com")
                .buildThenShowDialog(MainActivity.this)
        );
    }
}