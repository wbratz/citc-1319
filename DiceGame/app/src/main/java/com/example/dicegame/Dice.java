package com.example.dicegame;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class Dice implements Parcelable {

    private int dice0;
    private int dice1;
    private int dice2;

    private int totalScore;
    private int currentRollScore;
    private int scoreEnhancer;
    private boolean doubles;
    private boolean triples;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Dice(Parcel in) {
        dice0 = in.readInt();
        dice1 = in.readInt();
        dice2 = in.readInt();
        totalScore = in.readInt();
        currentRollScore = in.readInt();
        scoreEnhancer = in.readInt();
        doubles = in.readBoolean();
        triples = in.readBoolean();
    }

    public Dice() {

    }

    public String getTotalScore() {
        return String.valueOf(totalScore);
    }
    public String getCurrentRollScore() { return String.valueOf(currentRollScore); }
    public String getScoreEnhancer() { return String.valueOf(scoreEnhancer); }

    public boolean getDoubles() { return doubles; }
    public boolean getTriples() { return triples; }
    public void toggleDoubles() { doubles = !doubles;}
    public void toggleTriples() { triples = !triples;}


    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dice0);
        parcel.writeInt(dice1);
        parcel.writeInt(dice2);
        parcel.writeInt(totalScore);
        parcel.writeInt(currentRollScore);
        parcel.writeInt(scoreEnhancer);
        parcel.writeBoolean(doubles);
        parcel.writeBoolean(triples);
    }

    public int[] getDiceValues()
    {
        return new int[] { dice0, dice1, dice2 };
    }

    public void rollDice() {
        Random random = new Random();
        dice0 = random.nextInt(6) + 1;
        dice1 = random.nextInt(6) + 1;
        dice2 = random.nextInt(6) + 1;

        DetermineEnhancer();
        CalculateRollScore();
        CalculateTotalScore();
    }

    private void DetermineEnhancer()
    {

        if(dice0 == dice1 && dice0 == dice2 && triples)
        {
            scoreEnhancer = 100;
            return;
        }

        if((dice0 == dice1 || dice1 == dice2 || dice2 == dice0) && doubles)
        {
            scoreEnhancer = 50;
            return;
        }

        scoreEnhancer = 0;
    }

    private void CalculateTotalScore() { totalScore += currentRollScore; }
    private void CalculateRollScore () { currentRollScore = dice0 + dice1 + dice2 + scoreEnhancer; }
}
