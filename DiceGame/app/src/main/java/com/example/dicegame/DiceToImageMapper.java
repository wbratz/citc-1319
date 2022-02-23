package com.example.dicegame;

public class DiceToImageMapper {

    public int MapDiceToImage(int diceValue) {
        if(diceValue == 1) return R.drawable.die_1;
        if(diceValue == 2) return R.drawable.die_2;
        if(diceValue == 3) return R.drawable.die_3;
        if(diceValue == 4) return R.drawable.die_4;
        if(diceValue == 5) return R.drawable.die_5;
        if(diceValue == 6) return R.drawable.die_6;

        return 0;
    }
}
