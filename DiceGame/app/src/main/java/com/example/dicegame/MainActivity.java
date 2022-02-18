package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Dice diceData;
    private TextView DiceResultNumber;
    private TextView DiceResultNumber1;
    private TextView DiceResultNumber2;

    private TextView TotalScoreNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiceResultNumber = findViewById(R.id.diceResultNumber);
        DiceResultNumber1 = findViewById(R.id.diceResultNumber1);
        DiceResultNumber2 = findViewById(R.id.diceResultNumber2);
        TotalScoreNumber = findViewById(R.id.totalScoreNumber);
        Button rollDice = findViewById(R.id.rollDice);

        diceData = new Dice();

        // I replaced the function with a lambda here but I kept the function body down below
        // and named it appropriately.
        rollDice.setOnClickListener(view -> rollDiceBtnClicked(view));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("diceData", diceData);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        diceData = savedInstanceState.getParcelable("diceData");

        setUiValues();
    }

    public void rollDiceBtnClicked(View view) {
        diceData.rollDice();

        setUiValues();
    }

    private void setUiValues() {

        int[] diceValues = diceData.getDiceValues();

        DiceResultNumber.setText(String.valueOf(diceValues[0]));
        DiceResultNumber1.setText(String.valueOf(diceValues[1]));
        DiceResultNumber2.setText(String.valueOf(diceValues[2]));

        TotalScoreNumber.setText(diceData.getTotalScore());
    }
}