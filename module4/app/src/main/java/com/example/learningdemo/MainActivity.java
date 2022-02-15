package com.example.learningdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button testButton;
    private TextView sampleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testButton = findViewById(R.id.testButton);
        sampleTextView = findViewById(R.id.sampleTextView);

        //anonymous class?
        testButton.setOnClickListener(vw -> sampleTextView.setText(Integer.toString(5)));
    }
}