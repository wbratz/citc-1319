package com.example.learningdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
// QUESTION 5.1
// incompatible types: int cannot be converted to string:40

// QUESTION 5.3
// You can wrap the int in String.valueOf() or add "" + prior to the string to implicitly wrap it in sting.valueOf()

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.helloView);
        editText = (EditText) findViewById(R.id.editTextTextPersonName);
        textView.setVisibility(View.INVISIBLE);
        button = (Button) findViewById(R.id.button);
        button.setText("Greet");

        Log.i("Test123", "message");
        Log.d("another test", "Another Message");

        int Test = 1;
        Log.d("Int", String.valueOf(Test));

        Log.d("LifeCycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "onDestroy");
    }

    public void MessageFunction(View vw) {

        // assignment block start

        String helloWorld = "Hello World";
        Toast.makeText(this,
                "My message to the user is " + helloWorld,
                Toast.LENGTH_LONG)
                .show();

        Log.d("debugData", "My debug data is: " + helloWorld);

        // assignment block end

        if(editText.getText().length() == 0 && textView.getVisibility() == textView.INVISIBLE)
        {
            Toast.makeText(this, "Enter your name first!", Toast.LENGTH_LONG).show();
        }
        else {
            if(textView.getVisibility() == textView.VISIBLE) {
                textView.setVisibility(vw.INVISIBLE);
                button.setText("Greet");
            }
            else {
                button.setText("Hide");
                textView.setText("Hello " + editText.getText());
                textView.setVisibility(vw.VISIBLE);
            }

            editText.setText("");
        }
    }
}