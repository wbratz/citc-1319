package com.example.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManager implements Parcelable {

    private final List<Roll> rollList;
    private final String userName;
    private int doubles;
    private int triples;
    private final DiceManager _diceManager;
    private int totalScore;
    private final UUID gameId;

    public GameManager(String userName) {
        this.userName = userName;
        _diceManager = new DiceManager();
        rollList = new ArrayList<>();
        gameId = UUID.randomUUID();
    }

    protected GameManager(Parcel in) {
        rollList = in.createTypedArrayList(Roll.CREATOR);
        userName = in.readString();
        doubles = in.readInt();
        triples = in.readInt();
        _diceManager = in.readParcelable(DiceManager.class.getClassLoader());
        totalScore = in.readInt();
        gameId = UUID.fromString(in.readString());
    }

    public static final Creator<GameManager> CREATOR = new Creator<GameManager>() {
        @Override
        public GameManager createFromParcel(Parcel in) {
            return new GameManager(in);
        }

        @Override
        public GameManager[] newArray(int size) {
            return new GameManager[size];
        }
    };

    public int getTotalScore() {
        int totalScore = 0;
        
        for(Roll roll : rollList) {
            totalScore += roll.getScore();
        }

        return totalScore;
    }

    public String getUserName() {
        return userName;
    }

    public Roll getLastRoll() {
        return rollList.get(rollList.size() - 1);
    }

    public int getDiceRolls() {
        return rollList.size();
    }

    public Roll rollDice(DiceGameOptions diceGameOptions) {
        Roll rollResult = _diceManager.roll(diceGameOptions);

        if(rollResult.getHasDoubles())
            doubles++;
        if(rollResult.getHasTriples())
            triples++;

        totalScore += rollResult.getScore();

        rollList.add(rollResult);

        return rollResult;
    }

    public GameStats getGameStats() {
        return new GameStats(userName, rollList.size(), doubles, triples, totalScore);
    }

    public UUID getGameId() {
        return gameId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(rollList);
        parcel.writeString(userName);
        parcel.writeInt(doubles);
        parcel.writeInt(triples);
        parcel.writeParcelable(_diceManager, i);
        parcel.writeInt(totalScore);
        parcel.writeString(gameId.toString());
    }
}
