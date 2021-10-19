package ca.cmpt276.as3.model;


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

    public void calculateDimensions(String gameSize) {
        int row = 0;
        int col = 0;

        switch (gameSize) {
            case "4 rows x 6 columns":
                row = 4;
                col = 6;
                break;
            case "5 rows x 10 columns":
                row = 5;
                col = 10;
                break;
            case "6 rows x 15 columns":
                row = 6;
                col = 15;
                break;
        }
        setRow(row);
        setCol(col);
    }

    public void calculateHockeyCards(String hockeyCards) {
        int numOfHockeyCards = 0;

        switch (hockeyCards) {
            case "6 hockey cards":
                numOfHockeyCards = 6;
                break;
            case "10 hockey cards":
                numOfHockeyCards = 10;
                break;
            case "15 hockey cards":
                numOfHockeyCards = 15;
                break;
            case "20 hockey cards":
                numOfHockeyCards = 20;
                break;
        }
        setHockeyCardsNum(numOfHockeyCards);
    }


}
