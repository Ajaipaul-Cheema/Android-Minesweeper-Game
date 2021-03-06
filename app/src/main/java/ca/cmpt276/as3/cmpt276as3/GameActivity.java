package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityGameBinding;
import ca.cmpt276.as3.model.GameLogic;

/**
 * This class displays the appropriate game size & number of hockey cards
 * according to the options picked by user on the options page
 * It also randomizes placement of hockey cards and provides a number
 * on a non-mine button or on a mine that has already been revealed
 * It also shows a scanning animation across the button's row and column
 * and makes different sounds whether if it was a mine or not a mine
 * or a revealed mine being clicked again
 */
public class GameActivity extends AppCompatActivity {

    private GameLogic gameLogic;
    Bitmap scaledBitmap;
    Bitmap originalBitmap;
    Button[][] buttons;
    String[][] valuesGame;
    private static int NUM_ROWS, NUM_COLS;
    private int numCards;
    private int numFoundCards;
    private int scansUsed;
    int[][] temp;
    Resources resource;
    Button button;
    Animation animation;
    MediaPlayer noMine;
    MediaPlayer mineFound;

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, GameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ca.cmpt276.as3.cmpt276as3.databinding.ActivityGameBinding binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        gameLogic = GameLogic.getInstance();
        NUM_ROWS = gameLogic.getRow();
        NUM_COLS = gameLogic.getCol();
        buttons = new Button[NUM_ROWS][NUM_COLS];
        valuesGame = new String[NUM_ROWS][NUM_COLS];
        numCards = gameLogic.getHockeyCardsNum();
        numFoundCards = gameLogic.getNumFoundHockeyCards();
        scansUsed = gameLogic.getScanUsed();

        updateCardsFound();
        updateScanNum();
        populateButtons(buttons);
        setHockeyCards();

    }

    private void setHockeyCards() {
        Random random = new Random();

        for (int count = 0; count < gameLogic.getHockeyCardsNum(); count++) {
            int chosen_row = random.nextInt(NUM_ROWS);
            int chosen_col = random.nextInt(NUM_COLS);

            if (buttons[chosen_row][chosen_col].getText() != " ") {
                buttons[chosen_row][chosen_col].setText(" ");
            } else {
                count--;
            }
        }
    }

    private void populateButtons(Button [][] buttons) {
        TableLayout table = findViewById(R.id.Button_Table);
        noMine = MediaPlayer.create(this, R.raw.scangame);
        mineFound = MediaPlayer.create(this, R.raw.hockeycardrevealed);


        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++) {
                final int FINAL_Col = col;
                final int FINAL_Row = row;

                Button button = new Button(this);

                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(v -> gridButtonClicked(FINAL_Col, FINAL_Row));

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void updateCardsFound() {
        TextView cards = findViewById(R.id.description);
        String hockeyCardsFound = getString(R.string.found) + numFoundCards + getString(R.string.of) + numCards + getString(R.string.hockeyCards);
        cards.setText(hockeyCardsFound);
    }

    private void updateScanNum() {
        TextView scans = findViewById(R.id.scansUsed);
        String scansDone = getString(R.string.scansUsedNumber) + scansUsed;
        scans.setText(scansDone);
    }

    private void scanButtons() {
        temp = new int[NUM_ROWS][NUM_COLS];
        int counter = 0;
        int temp_col, temp_row;

        for (temp_row = 0; temp_row < NUM_ROWS; temp_row++) {
            for (temp_col = 0; temp_col < NUM_COLS; temp_col++) {

                for (int col = 0; col < NUM_COLS; col++) {
                    if (buttons[temp_row][col].getText() == " ") {
                        counter++;
                    }
                }

                for (int row = 0; row < NUM_ROWS; row++) {
                    if (buttons[row][temp_col].getText() == " ") {
                        counter++;
                    }
                }

                temp[temp_row][temp_col] = counter;
                valuesGame[temp_row][temp_col] = Integer.toString(temp[temp_row][temp_col]);
                counter = 0;
            }
            counter = 0;
        }
    }

    private void updateNumbersForButtons() {
        for (int rows = 0; rows < NUM_ROWS; rows++) {
            for (int cols = 0; cols < NUM_COLS; cols++) {
                String textValue = buttons[rows][cols].getText().toString();
                if (textValue.matches(getString(R.string.regexValues))) {
                    buttons[rows][cols].setText(valuesGame[rows][cols]);
                }
            }
        }
    }

    private void scanAnimationHelper(int col, int row) {
        animation = AnimationUtils.loadAnimation(GameActivity.this,R.anim.scan_animation);
        int[][] temp = new int[NUM_ROWS][NUM_COLS];

        for(int j = 0; j < temp[row].length; j++) {
            buttons[row][j].startAnimation(animation);
        }

        for(int j = 0; j < temp.length; j++) {
            buttons[j][col].startAnimation(animation);
        }
    }

    private void gridButtonClicked(int col, int row) {
        button = buttons[row][col];

        if(button.getText().toString().equals(" ")) {
            mineFound.start();
            scanButtons();
            updateNumbersForButtons();
            lockButtonSizes();
            numFoundCards++;
            updateCardsFound();

            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gretzkyminepicture);
            scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            if (numFoundCards == numCards) {
                winAlert();
            }

            button.setText("  ");
        }

        else if (button.getText().toString().equals("  ")) {
            noMine.start();
            scanAnimationHelper(col, row);
            button.setText(valuesGame[row][col]);
            scansUsed++;
            updateCardsFound();
            updateScanNum();
        }

        else if(button.getText().toString().equals(valuesGame[row][col])) {
            assert true;
        }

        else {
            noMine.start();
            scanAnimationHelper(col, row);
            scanButtons();
            updateNumbersForButtons();
            button.setText(valuesGame[row][col]);
            scansUsed++;
            updateScanNum();
        }

        scanButtons();
        updateNumbersForButtons();
    }

    private void winAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.gameOverTitle);

        alertDialogBuilder.setMessage(R.string.congratulate)
                .setCancelable(false)
                .setIcon(R.drawable.hockeygameover)
                .setPositiveButton(R.string.returnMessage, (dialog, id) -> finish());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
}