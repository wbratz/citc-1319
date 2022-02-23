package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Dice diceData;
    private DiceToImageMapper diceMapper;

    private ImageView Dice1Image;
    private ImageView Dice2Image;
    private ImageView Dice3Image;

    private TextView TotalScoreNumber;
    private TextView CurrentRollScore;
    private TextView ScoreEnhancer;

    private CheckBox DoublesCheckbox;
    private CheckBox TriplesCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dice1Image = findViewById(R.id.dice1image);
        Dice2Image = findViewById(R.id.dice2image);
        Dice3Image = findViewById(R.id.dice3image);

        TotalScoreNumber = findViewById(R.id.totalScoreText);
        CurrentRollScore = findViewById(R.id.diceRollResult);
        ScoreEnhancer = findViewById(R.id.scoreEnhancer);

        Button rollDice = findViewById(R.id.rollDice);

        DoublesCheckbox = findViewById(R.id.doublesCheckbox);
        TriplesCheckbox = findViewById(R.id.triplesCheckbox);

        diceData = new Dice();
        diceMapper = new DiceToImageMapper();

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
        EvaluateCheckboxStates();
        diceData.rollDice();

        setUiValues();
    }

    private void setUiValues() {

        int[] diceValues = diceData.getDiceValues();

        for (int i = 0; i < diceValues.length; i++) {
            int mapResult = diceMapper.MapDiceToImage(diceValues[i]);

            if(mapResult < 1)
                return;

            if(i == 0)
                Dice1Image.setImageResource(mapResult);
            if(i == 1)
                Dice2Image.setImageResource(mapResult);
            if(i == 2)
                Dice3Image.setImageResource(mapResult);
        }

        String scoreEnhancerResult = diceData.getScoreEnhancer();

        ScoreEnhancer.setText("No Score Enhancer");

        if(scoreEnhancerResult.length() > 1)
        {
            if(scoreEnhancerResult.length() == 2)
                ScoreEnhancer.setText("Double +50");

            if(scoreEnhancerResult.length() == 3)
                ScoreEnhancer.setText("Triple +100");
        }

        CurrentRollScore.setText("Score this Roll: " + diceData.getCurrentRollScore());
        TotalScoreNumber.setText("Score: " + diceData.getTotalScore());
    }

    private void EvaluateCheckboxStates()
    {
        if(DoublesCheckbox.isChecked() != diceData.getDoubles())
            diceData.toggleDoubles();

        if(TriplesCheckbox.isChecked() != diceData.getTriples())
            diceData.toggleTriples();
    }
}