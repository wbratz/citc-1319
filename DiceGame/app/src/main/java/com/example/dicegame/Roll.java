package com.example.dicegame;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Roll implements Parcelable {
    private int[] rollValues;
    private boolean hasDoubles;
    private boolean hasTriples;
    private int score;

    protected Roll(Parcel in) {
        rollValues = in.createIntArray();
        hasDoubles = in.readByte() != 0;
        hasTriples = in.readByte() != 0;
        score = in.readInt();
    }

    public static final Creator<Roll> CREATOR = new Creator<Roll>() {
        @Override
        public Roll createFromParcel(Parcel in) {
            return new Roll(in);
        }

        @Override
        public Roll[] newArray(int size) {
            return new Roll[size];
        }
    };

    public int[] getRollValues() {
        return this.rollValues;
    }

    public boolean getHasTriples() {
        return this.hasTriples;
    }

    public boolean getHasDoubles() {
        return this.hasDoubles;
    }

    public int getScore() {
        return score;
    }

    public Roll(int[] rollValues, boolean hasTriples, boolean hasDoubles) {
        this.rollValues = rollValues;
        this.hasTriples = hasTriples;
        this.hasDoubles = hasDoubles;

        score = calculateRollScore() + calculateRollScoreEnhancer();
    }

    private int calculateRollScoreEnhancer() {
        if (hasTriples) {
            return 100;
        }

        if (hasDoubles) {
            return 50;
        }

        return 0;
    }

    private int calculateRollScore() {
        int rollScore = 0;

        for (int i = 0; i < rollValues.length; i++) {
            rollScore += rollValues[i];
        }

        return rollScore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(rollValues);
        parcel.writeBoolean(hasDoubles);
        parcel.writeBoolean(hasTriples);
        parcel.writeInt(score);
    }
}
