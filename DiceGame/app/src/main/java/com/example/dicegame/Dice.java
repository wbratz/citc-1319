package com.example.dicegame;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Random;

public class Dice implements Parcelable {

    private int dice0;
    private int dice1;
    private int dice2;

    private int totalScore;

    protected Dice(Parcel in) {
        dice0 = in.readInt();
        dice1 = in.readInt();
        dice2 = in.readInt();
        totalScore = in.readInt();
    }

    public Dice() {

    }

    public String getTotalScore() {
        return String.valueOf(totalScore);
    }

    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dice0);
        parcel.writeInt(dice1);
        parcel.writeInt(dice2);
        parcel.writeInt(totalScore);
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

        CalculateTotalScore();
    }

    private void CalculateTotalScore() {
        totalScore += dice0 + dice1 + dice2;
    }
}
