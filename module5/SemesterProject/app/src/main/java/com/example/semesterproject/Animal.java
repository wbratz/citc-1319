package com.example.semesterproject;

public class Animal {
    private int UnicodeValue;
    private int SoundFile;
    private boolean IsChosen = false;
    private boolean WasChosenLast = false;

    public Animal (int UnicodeValue, int SoundFile)
    {
        this.UnicodeValue = UnicodeValue;
        this.SoundFile = SoundFile;
    }

    public int getUnicodeValue() {
        return UnicodeValue;
    }

    public void setUnicodeValue(int unicodeValue) {
        UnicodeValue = unicodeValue;
    }

    public int getSoundFile() {
        return SoundFile;
    }

    public void setSoundFile(int soundFile) {
        SoundFile = soundFile;
    }

    public void setIsChosen(boolean isChosen) {
        IsChosen = isChosen;
    }

    public boolean getIsChosen() {
        return IsChosen;
    }

    public void setWasChosenLast(boolean chosenLast) {
        WasChosenLast = chosenLast;
    }

    public boolean getWasChosenLast() {
        return WasChosenLast;
    }
}
