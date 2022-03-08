package com.example.dicegame;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class DiceGameOptions implements Parcelable {
    private boolean _doubleEnabled;
    private boolean _tripleEnabled;

    public DiceGameOptions(){
        _doubleEnabled = false;
        _tripleEnabled = false;
    }

    public boolean getDoubleEnabled() {
        return _doubleEnabled;
    }

    public boolean getTripleEnabled() {
        return _tripleEnabled;
    }

    public void setDoubleEnabled(boolean doubleEnabled) {
        _doubleEnabled = doubleEnabled;
    }

    public void setTripleEnabled(boolean tripleEnabled) {
        _tripleEnabled = tripleEnabled;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected DiceGameOptions(Parcel in) {
        _doubleEnabled = in.readBoolean();
        _tripleEnabled = in.readBoolean();
    }

    public static final Creator<DiceGameOptions> CREATOR = new Creator<DiceGameOptions>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public DiceGameOptions createFromParcel(Parcel in) {
            return new DiceGameOptions(in);
        }

        @Override
        public DiceGameOptions[] newArray(int size) {
            return new DiceGameOptions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(_doubleEnabled);
        parcel.writeBoolean(_tripleEnabled);
    }
}
