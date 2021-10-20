package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;


import ca.cmpt276.as3.cmpt276as3.databinding.ActivityMenuScreenBinding;
import ca.cmpt276.as3.model.GameLogic;

/**
 * This class has three buttons (play game, help and options)
 * to go to each of those activities
 * It also handles saving the options between application executions
 * by using the calling getter functions of options activity & saving them
 * to game logic attributes by use of getInstance (singleton class model)
 */
public class MenuScreenActivity extends AppCompatActivity {

    private GameLogic gameLogic;

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, MenuScreenActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ca.cmpt276.as3.cmpt276as3.databinding.ActivityMenuScreenBinding binding = ActivityMenuScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        gameLogic = GameLogic.getInstance();

        launchingGame();
        launchingHelp();
        launchingOptions();
        registerOptions();

    }

    private void launchingGame() {
        Button launchGame = findViewById(R.id.playGame);
        launchGame.setOnClickListener(v -> {
            Intent i = GameActivity.makeLaunchIntent(MenuScreenActivity.this);
            startActivity(i);
        });
    }

    private void launchingHelp() {
        Button launchHelp = findViewById(R.id.help);
        launchHelp.setOnClickListener(v -> {
            Intent i = HelpActivity.makeLaunchIntent(MenuScreenActivity.this);
            startActivity(i);
        });
    }

    private void launchingOptions() {
        Button launchOptions = findViewById(R.id.options);
        launchOptions.setOnClickListener(v -> {
            Intent i = OptionsActivity.makeLaunchIntent(MenuScreenActivity.this);
            startActivity(i);
        });
    }

    private void registerOptions() {
        String gameSize = OptionsActivity.getGameSize(this);
        String hockeyCardsNum = OptionsActivity.getNumHockeyCards(this);
        gameLogic.calculateDimensions(gameSize);
        gameLogic.calculateHockeyCards(hockeyCardsNum);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerOptions();
    }
}