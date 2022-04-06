package com.example.dicegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener {

    private GameManager _gameManager;
    private DiceToImageMapper diceMapper;
    private DiceGameOptions _diceOptions;
    private SharedPreferences.Editor _editor;
    private GestureDetectorCompat gestureDetector;

    private static int MIN_SWIPE_DISTANCE_Y = 100;
    private static int MAX_SWIPE_DISTANCE_Y = 2000;

    //Did you hear about the fire at the camp ground, it was in tents
    private Intent _scoreboardIntent;
    private ImageView Dice1Image;
    private ImageView Dice2Image;
    private ImageView Dice3Image;

    private TextView TotalScoreNumber;
    private TextView CurrentRollScore;
    private TextView ScoreEnhancer;
    private EditText Name;
    private KonfettiView konfettiView;
    private Shape.DrawableShape drawableShape;
    private MediaPlayer rollDiceSound;
    private MediaPlayer cheerSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // animation
        // used a library to drop Confetti on triple.
        // https://github.com/DanielMartinus/Konfetti
        // Why? because its cooler and easier than I could make on my own :)
        konfettiView = findViewById(R.id.konfettiView);
        configureKonfetti();

        //media player
        rollDiceSound = MediaPlayer.create(MainActivity.this, R.raw.roll);
        cheerSound = MediaPlayer.create(MainActivity.this, R.raw.applause7);

        //swipe to roll
        gestureDetector = new GestureDetectorCompat(this, this);
        InitUiItems();
        SetListeners();
    }

    private void configureKonfetti() {
        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        drawableShape = new Shape.DrawableShape(drawable, true);
        EmitterConfig emitterConfig = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);
        Party party = new PartyFactory(emitterConfig)
                .angle(270)
                .spread(90)
                .setSpeedBetween(1f, 5f)
                .timeToLive(2000L)
                .shapes(new Shape.Rectangle(0.2f), drawableShape)
                .sizes(new Size(12, 5f, 0.2f))
                .position(0.0, 0.0, 1.0, 0.0)
                .build();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.gestureDetector.onTouchEvent(event)) {
            return true;
        }

        return super.onTouchEvent(event);
    }

    private void SetListeners() {
        findViewById(R.id.scoreboard).setOnClickListener(view -> launchScoreboardIntent());

        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                _gameManager = new GameManager(Name.getText().toString());
            }
        });
    }

    private void InitUiItems() {
        Dice1Image = findViewById(R.id.dice1image);
        Dice2Image = findViewById(R.id.dice2image);
        Dice3Image = findViewById(R.id.dice3image);

        _scoreboardIntent = new Intent(getBaseContext(), Scoreboard.class);

        TotalScoreNumber = findViewById(R.id.totalScoreText);
        CurrentRollScore = findViewById(R.id.diceRollResult);
        ScoreEnhancer = findViewById(R.id.scoreEnhancer);
        Name = findViewById(R.id.name);

        _diceOptions = new DiceGameOptions();
        diceMapper = new DiceToImageMapper();

        SharedPreferences sharedPreferences = getSharedPreferences("gameStats", MODE_PRIVATE);
        _editor = sharedPreferences.edit();
    }

    private void reset() {
        _diceOptions = new DiceGameOptions();
        TotalScoreNumber.setText(String.format("Score: %s", 0));
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
        outState.putParcelable("gameManager", _gameManager);
        outState.putParcelable("diceOptions", _diceOptions);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        _gameManager = savedInstanceState.getParcelable("gameManager");
        _diceOptions = savedInstanceState.getParcelable("diceOptions");
        setUiValues(_gameManager.getLastRoll());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                SettingsDialog settingsDialog = new SettingsDialog();
                settingsDialog.show(getSupportFragmentManager(), "");
                break;
            case R.id.about:
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.show(getSupportFragmentManager(), "");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void setDiceOptions(DiceGameOptions diceOptions) {
        _diceOptions = diceOptions;
    }

    public DiceGameOptions getDiceOptions() {
        return _diceOptions;
    }

    private void rollDiceBtnClicked() {
        if (Name.getText().length() < 1) {
            Toast.makeText(this, "You must input a name before rolling", Toast.LENGTH_LONG).show();
            return;
        }

        rollDiceSound.start();
        Roll roll = _gameManager.rollDice(_diceOptions);
        Gson gson = new Gson();
        String json = gson.toJson(_gameManager.getGameStats());
        _editor.putString(_gameManager.getGameId().toString(), json);
        _editor.apply();
        setUiValues(roll);
    }

    private void launchScoreboardIntent() {
        String name = Name.getText().length() > 0 ? Name.getText().toString() : "";
        int diceRolls = _gameManager == null ? 0 : _gameManager.getDiceRolls();

        _scoreboardIntent.putExtra("name", name);
        _scoreboardIntent.putExtra("diceRolls", diceRolls);
        startActivity(_scoreboardIntent);
    }

    private void setUiValues(Roll roll) {

        int[] rollValues = roll.getRollValues();

        if(Name.getText().length() < 1)
        {
            Name.setText(_gameManager.getUserName());
        }

        for (int i = 0; i < rollValues.length; i++) {
            int mapResult = diceMapper.MapDiceToImage(rollValues[i]);

            if(mapResult < 1)
                return;

            MapImage(i, mapResult);
        }

        EvaluateBonus(roll);

        CurrentRollScore.setText(String.format("%s%s", getString(R.string.scoreThisRoll)+" ", roll.getScore()));
        TotalScoreNumber.setText(String.format("Score: %s", _gameManager.getTotalScore()));
    }

    private void MapImage(int i, int mapResult) {
        if(i == 0)
            Dice1Image.setImageResource(mapResult);
        if(i == 1)
            Dice2Image.setImageResource(mapResult);
        if(i == 2)
            Dice3Image.setImageResource(mapResult);
    }

    private void EvaluateBonus(Roll roll) {
        if(roll.getHasTriples()) {
            rain();
            cheerSound.start();
            ScoreEnhancer.setText(R.string.tripleBonus);
        }
        else if(roll.getHasDoubles()) {
            ScoreEnhancer.setText(R.string.doubleBonus);
        }
        else {
            ScoreEnhancer.setText(R.string.noScore);
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        float deltaY = motionEvent.getY() - motionEvent1.getY();

        float deltaYAbs = Math.abs(deltaY);

        if((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y))
        {
            if(deltaY > 0)
            {
                rollDiceBtnClicked();
            }

            if(deltaY < 0)
            {
                rollDiceBtnClicked();
            }
        }

        return true;
    }

    public void rain() {
        EmitterConfig emitterConfig = new Emitter(5, TimeUnit.SECONDS).perSecond(100);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .angle(Angle.BOTTOM)
                        .spread(Spread.ROUND)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 15f)
                        .position(new Position.Relative(0.0, 0.0).between(new Position.Relative(1.0, 0.0)))
                        .build()
        );
    }
}