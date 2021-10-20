package ca.cmpt276.as3.cmpt276as3;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityOptionsBinding;

import ca.cmpt276.as3.model.GameLogic;

/**
 * This class displays the game size and number of hockey cards options
 * It also creates the board size and number of hockey cards by what was selected
 * and saves those selected options to be rendered into the game activity
 */
public class OptionsActivity extends AppCompatActivity {

    GameLogic gameLogic;
    private static final String GAME_SIZE_PREF = "Num of game size";
    private static final String GAME_PREF = "AppPrefs";
    private static final String NUM_HOCKEY_CARDS_PREF = "Num of hockey cards";
    private static final String HOCKEY_CARDS_PREF = "Prefs";

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, OptionsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ca.cmpt276.as3.cmpt276as3.databinding.ActivityOptionsBinding binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        gameLogic = GameLogic.getInstance();

        createBoardSize();
        createNumHockeyCards();
    }

    private void createBoardSize() {
        RadioGroup group = findViewById(R.id.radio_group_size);
        String[] gameSizeOptions = getResources().getStringArray(R.array.game_size_options);

        for (String i : gameSizeOptions) {
            final String gameSize = i;

            RadioButton button = new RadioButton(this);
            button.setText(i);

            button.setOnClickListener(v -> {
                saveGameSize(gameSize);
                gameLogic.calculateDimensions(gameSize);
            });

            group.addView(button);

            if (gameSize.equals(getGameSize(this))) {
                button.setChecked(true);
            }
        }
    }

    private void createNumHockeyCards() {
        RadioGroup group = findViewById(R.id.radio_group_num_cards);
        String[] num_hockey_cards = getResources().getStringArray(R.array.num_hockey_cards);

        for (String i : num_hockey_cards) {
            final String num_cards = i;

            RadioButton button = new RadioButton(this);
            button.setText(i);

            button.setOnClickListener(v -> {
                saveNumOfHockeyCards(num_cards);
                gameLogic.calculateHockeyCards(num_cards);
            });

            group.addView(button);

            if (num_cards.equals(getNumHockeyCards(this))) {
                button.setChecked(true);
            }
        }
    }

    private void saveGameSize(String num_box) {
        SharedPreferences prefs = this.getSharedPreferences(GAME_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(GAME_SIZE_PREF, num_box);
        editor.apply();
    }

    public static String getGameSize(Context context) {
        String gameSize = context.getResources().getString(R.string.default_game_size);
        SharedPreferences prefs = context.getSharedPreferences(GAME_PREF, MODE_PRIVATE);
        return prefs.getString(GAME_SIZE_PREF, gameSize);
    }

    private void saveNumOfHockeyCards(String num_cards) {
        SharedPreferences prefs = this.getSharedPreferences(HOCKEY_CARDS_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(NUM_HOCKEY_CARDS_PREF, num_cards);
        editor.apply();
    }

    public static String getNumHockeyCards(Context context) {
        String hockeyCardsCount = context.getResources().getString(R.string.default_num_hockey_cards);
        SharedPreferences prefs = context.getSharedPreferences(HOCKEY_CARDS_PREF, MODE_PRIVATE);
        return prefs.getString(NUM_HOCKEY_CARDS_PREF, hockeyCardsCount);
    }
}

