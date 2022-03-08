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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Dice(Parcel in) {
        dice0 = in.readInt();
        dice1 = in.readInt();
        dice2 = in.readInt();
    }

    public Dice() {
    }

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
    }

    public Roll rollDice(DiceGameOptions diceOptions) {
        Random random = new Random();
        dice0 = random.nextInt(6) + 1;
        dice1 = random.nextInt(6) + 1;
        dice2 = random.nextInt(6) + 1;

        return new Roll( new int[] { dice0, dice1, dice2 }, determineHasTriple(diceOptions), determineHasDouble(diceOptions));
    }
    private boolean determineHasDouble(DiceGameOptions diceOptions) {
        return diceOptions.getDoubleEnabled() && (dice0 == dice1 || dice1 == dice2 || dice2 == dice0) && !determineHasTriple(diceOptions);
    }

    private boolean determineHasTriple(DiceGameOptions diceOptions) {
        return diceOptions.getTripleEnabled() && dice0 == dice1 && dice0 == dice2;
    }
}
