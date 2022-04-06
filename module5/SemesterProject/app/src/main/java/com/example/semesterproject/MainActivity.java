package com.example.semesterproject;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
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

public class MainActivity extends AppCompatActivity {

    private Map _animalDictionary;
    private final Random _random = new Random();
    private TextView _toMatchImage;
    private Button _leftButton;
    private Button _rightButton;
    private Button _centerButton;
    private int _correctUnicode;
    private int _correctButtonInt;
    private MediaPlayer _correctAnimalNoise;
    private MediaPlayer _leftAnimalNoise;
    private MediaPlayer _centerAnimalNoise;
    private MediaPlayer _rightAnimalNoise;

    private KonfettiView konfettiView = null;
    private Shape.DrawableShape drawableShape = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _toMatchImage = findViewById(R.id.textView);
        _leftButton = findViewById(R.id.leftButton);
        _rightButton = findViewById(R.id.rightButton);
        _centerButton = findViewById(R.id.centerButton);

        AnimalDictionary animalDictionary = new AnimalDictionary();

        _animalDictionary = animalDictionary.getAnimalDictionary();

        selectToMatch();
        setToMatchImage();
        setCorrectButton();
        setIncorrectButtons();

        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        drawableShape = new Shape.DrawableShape(drawable, true);

        konfettiView = findViewById(R.id.konfettiView);
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

        // MVP
        // TODO mechanism for making sure we don't select the same image twice
        // TODO wire up correct choice correct button
        // TODO add cheer when correct choice is made
        // TODO parcelable

        // NICE TO HAVE
        // TODO add more images
        // TODO add selectable categories
        // TODO make main screen with category selection
        // TODO make settings to change categories
        // TODO make streak score
        // TODO add sounds

    }

    private String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

    private void setToMatchImage() {
        _toMatchImage.setText(getEmojiByUnicode(_correctUnicode));
    }

    private void selectToMatch() {
        _correctUnicode = getRandomUnicode();
    }

    private void setCorrectButton() {
        _correctButtonInt = getRandomButton();

        if (_correctButtonInt == 1) {
            setButtonCorrect(_leftButton);
        }

        if (_correctButtonInt == 2 ) {
            setButtonCorrect(_centerButton);
        }

        if (_correctButtonInt == 3) {
            setButtonCorrect(_rightButton);
        }
    }

    private void setButtonCorrect(Button button) {
        button.setText(getEmojiByUnicode(_correctUnicode));
        button.setOnClickListener(view -> {
            explode();

            selectToMatch();
            setToMatchImage();
            setCorrectButton();
            setIncorrectButtons();
            //playCorrectAnimalSound();
        });
    }

    private void playCorrectAnimalSound(Animal animal) {
        _correctAnimalNoise.create(MainActivity.this, animal.getSoundFile());
    }

    private void setIncorrectButtons() {
        if (_correctButtonInt == 1) {
            setIncorrectButtonImages(_rightButton, _centerButton);
            _rightButton.setOnClickListener(null);
            _centerButton.setOnClickListener(null);
        }

        if (_correctButtonInt == 2) {
            setIncorrectButtonImages(_rightButton, _leftButton);
            _rightButton.setOnClickListener(null);
            _leftButton.setOnClickListener(null);
        }

        if (_correctButtonInt == 3) {
            setIncorrectButtonImages(_leftButton, _centerButton);
            _centerButton.setOnClickListener(null);
            _leftButton.setOnClickListener(null);
        }
    }

    private void setIncorrectButtonImages(Button leftButton, Button centerButton) {
        int wrongUnicode = getNonMatchingImageUnicode(0);
        int wrongUnicode2 = getNonMatchingImageUnicode(wrongUnicode);
        leftButton.setText(getEmojiByUnicode(wrongUnicode));
        centerButton.setText(getEmojiByUnicode(wrongUnicode2));
    }

    private Animal getRandomAnimal() {
        Object key = _animalDictionary.keySet().toArray()[getRandomDictionaryValue()];
        Object value = _animalDictionary.get(key);

        return (Animal)value;
    }

    private int getRandomUnicode() {
        Object key = _animalDictionary.keySet().toArray()[getRandomDictionaryValue()];
        Object value = _animalDictionary.get(key);

        Animal animal = (Animal)value;

        return animal.getUnicodeValue();
    }

    private int getRandomDictionaryValue() {
        return _random.nextInt(_animalDictionary.size());
    }

    private int getRandomButton() {
        return _random.nextInt(3) + 1;
    }

    private int getNonMatchingImageUnicode(int wrongUnicode)
    {
        int returnUnicode = getRandomUnicode();

        while (returnUnicode == _correctUnicode || returnUnicode == wrongUnicode)
        {
            returnUnicode = getRandomUnicode();
        }

        return returnUnicode;
    }

    public void explode() {
        EmitterConfig emitterConfig = new Emitter(100L, TimeUnit.MILLISECONDS).max(100);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .spread(360)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 30f)
                        .position(new Position.Relative(0.5, 0.3))
                        .build()
        );
    }

    public void parade() {
        EmitterConfig emitterConfig = new Emitter(5, TimeUnit.SECONDS).perSecond(30);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .angle(Angle.RIGHT - 45)
                        .spread(Spread.SMALL)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(10f, 30f)
                        .position(new Position.Relative(0.0, 0.5))
                        .build(),
                new PartyFactory(emitterConfig)
                        .angle(Angle.LEFT + 45)
                        .spread(Spread.SMALL)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(10f, 30f)
                        .position(new Position.Relative(1.0, 0.5))
                        .build()
        );
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