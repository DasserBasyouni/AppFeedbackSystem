package com.tech.futureteric.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tech.futureteric.feedbacklibrary.FeedbackSystemBuilder;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tech.futureteric.feedbacklibrary.constants.LibEnums.ALL;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FeedbackSystemBuilder()
                        .withSections(new int[]{LibEnums.ALL})
                        .buildThenShowDialog(MainActivity.this);
            }
        });

    }
}
