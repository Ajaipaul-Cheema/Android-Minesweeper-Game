package ca.cmpt276.as3.model;

import android.widget.Button;

import java.util.Random;

public class GameLogic {
    private static GameLogic instance;
    private int row;
    private int col;
    private int HockeyCardsNum;
    private int ScanUsed;
    private int NumFoundHockeyCards;

    private GameLogic() {
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public int getHockeyCardsNum() {
        return HockeyCardsNum;
    }

    public void setHockeyCardsNum(int hockeyCardsNum) {
        HockeyCardsNum = hockeyCardsNum;
    }

    public int getScanUsed() {
        return ScanUsed;
    }

    public int getNumFoundHockeyCards() {
        return NumFoundHockeyCards;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}