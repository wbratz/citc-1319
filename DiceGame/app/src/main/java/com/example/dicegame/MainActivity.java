package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int TotalScore;
    private int DiceRollResult;
    private TextView DiceResultNumber;
    private TextView TotalScoreNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiceResultNumber = findViewById(R.id.diceResultNumber);
        TotalScoreNumber = findViewById(R.id.totalScoreNumber);
        Button rollDice = findViewById(R.id.rollDice);


        // I replaced the function with a lambda here but I kept the function body down below
        // and named it appropriately.
        rollDice.setOnClickListener(view -> {
            {
                rollDiceBtnClicked(view);
            }
        });
    }

    public void rollDiceBtnClicked(View view) {
        Random random = new Random();

        int dice1 = random.nextInt(6 - 1 + 1) + 1;

        SetDiceRollResult(dice1);
        CalculateTotalScore();

        DiceResultNumber.setText(GetDiceRollResult());
        TotalScoreNumber.setText(GetTotalScore());
    }

    public String GetDiceRollResult() {
        return String.valueOf(DiceRollResult);
    }

    public String GetTotalScore() {
        return String.valueOf(TotalScore);
    }

    public void SetDiceRollResult(int val) {
        DiceRollResult = val;
    }

    public void CalculateTotalScore() {
        TotalScore += DiceRollResult;
    }
}