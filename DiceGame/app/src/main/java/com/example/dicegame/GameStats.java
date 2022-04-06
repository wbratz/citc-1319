package com.example.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

public class GameStats implements Parcelable {

    private final String _userName;
    private final int _totalRolls;
    private final int _totalDoubles;
    private final int _totalTriples;
    private final int _totalScore;

    protected GameStats(Parcel in) {
        _userName = in.readString();
        _totalRolls = in.readInt();
        _totalDoubles = in.readInt();
        _totalTriples = in.readInt();
        _totalScore = in.readInt();
    }

    public GameStats(String username, int totalRolls, int totalDoubles, int totalTriples, int totalScore) {
        _userName = username;
        _totalRolls = totalRolls;
        _totalDoubles = totalDoubles;
        _totalTriples = totalTriples;
        _totalScore = totalScore;
    }

    public static final Creator<GameStats> CREATOR = new Creator<GameStats>() {
        @Override
        public GameStats createFromParcel(Parcel in) {
            return new GameStats(in);
        }

        @Override
        public GameStats[] newArray(int size) {
            return new GameStats[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_userName);
        parcel.writeInt(_totalDoubles);
        parcel.writeInt(_totalRolls);
        parcel.writeInt(_totalTriples);
        parcel.writeInt(_totalScore);
    }

    public String getUserName() {
        return _userName;
    }

    public int getTotalDoubles() {
        return _totalDoubles;
    }

    public int getTotalTriples() {
        return _totalTriples;
    }

    public int getTotalScore() {
        return _totalScore;
    }
}
