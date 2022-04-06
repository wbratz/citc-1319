package com.example.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

public class DiceManager implements Parcelable {

    private final Dice _dice;
    private Roll _roll;

    public DiceManager() {
        _dice = new Dice();
    }

    protected DiceManager(Parcel in) {
        _dice = in.readParcelable(Dice.class.getClassLoader());
        _roll = in.readParcelable(Roll.class.getClassLoader());
    }

    public static final Creator<DiceManager> CREATOR = new Creator<DiceManager>() {
        @Override
        public DiceManager createFromParcel(Parcel in) {
            return new DiceManager(in);
        }

        @Override
        public DiceManager[] newArray(int size) {
            return new DiceManager[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(_dice, i);
        parcel.writeParcelable(_roll, i);
    }

    public Roll roll(DiceGameOptions diceOptions) {
        _roll = _dice.rollDice(diceOptions);
        return _roll;
    }
}
