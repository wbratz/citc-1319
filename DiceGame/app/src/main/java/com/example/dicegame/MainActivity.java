package com.example.dicegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DiceManager _diceManager;
    private DiceToImageMapper diceMapper;
    private DiceGameOptions _diceOptions;
    private int _score;

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

        _diceManager = new DiceManager();
        _diceOptions = new DiceGameOptions();

        diceMapper = new DiceToImageMapper();
        setDoubles();
        setTriples();

        // I replaced the function with a lambda here but I kept the function body down below
        // and named it appropriately.
        rollDice.setOnClickListener(view -> rollDiceBtnClicked(view));
        DoublesCheckbox.setOnClickListener(view -> setDoubles());
        TriplesCheckbox.setOnClickListener(view -> setTriples());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_example, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("diceManager", _diceManager);
        outState.putInt("score", _score);
        outState.putParcelable("diceOptions", _diceOptions);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        _diceManager = savedInstanceState.getParcelable("diceManager");
        _score = savedInstanceState.getInt("score");
        _diceOptions = savedInstanceState.getParcelable("diceOptions");

        setUiValues(_diceManager.getLastRoll());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                SettingsDialog settingsDialog = new SettingsDialog();
                settingsDialog.show(getSupportFragmentManager(), "");
                setDoubles();
                setTriples();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTriples() {
        _diceOptions.setTripleEnabled(TriplesCheckbox.isChecked());
    }

    private void setDoubles() {
        _diceOptions.setDoubleEnabled(DoublesCheckbox.isChecked());
    }

    public void setDiceOptions(DiceGameOptions diceOptions) {
        _diceOptions = diceOptions;

        TriplesCheckbox.setChecked(_diceOptions.getTripleEnabled());
        DoublesCheckbox.setChecked(_diceOptions.getDoubleEnabled());
    }

    public DiceGameOptions getDiceOptions() {
        return _diceOptions;
    }

    private void rollDiceBtnClicked(View view) {
        Roll roll = _diceManager.roll(_diceOptions);

        setUiValues(roll);
    }

    private void setUiValues(Roll roll) {

        int[] rollValues = roll.getRollValues();

        for (int i = 0; i < rollValues.length; i++) {
            int mapResult = diceMapper.MapDiceToImage(rollValues[i]);

            if(mapResult < 1)
                return;

            if(i == 0)
                Dice1Image.setImageResource(mapResult);
            if(i == 1)
                Dice2Image.setImageResource(mapResult);
            if(i == 2)
                Dice3Image.setImageResource(mapResult);
        }

        if(roll.getHasTriples()) {
            ScoreEnhancer.setText(R.string.tripleBonus);
        }
        else if(roll.getHasDoubles()) {
            ScoreEnhancer.setText(R.string.doubleBonus);
        }
        else {
            ScoreEnhancer.setText(R.string.noScore);
        }

        _score += roll.getScore();

        CurrentRollScore.setText(String.format("%s%s", getString(R.string.scoreThisRoll)+" ", roll.getScore()));
        TotalScoreNumber.setText(String.format("Score: %s", _score));
    }
}