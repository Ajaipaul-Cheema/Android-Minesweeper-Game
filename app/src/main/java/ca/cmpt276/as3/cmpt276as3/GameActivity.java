package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityGameBinding;
import ca.cmpt276.as3.model.GameLogic;

public class GameActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityGameBinding binding;
    private GameLogic gameLogic;
    Button[][] buttons;
    String[][] gameValues;
    private static int NUM_ROWS, NUM_COLS;
    private int numCards, numFoundCards, scansUsed;

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        gameLogic = GameLogic.getInstance();
        NUM_ROWS = gameLogic.getRow();
        NUM_COLS = gameLogic.getCol();
        buttons = new Button[NUM_ROWS][NUM_COLS];
        gameValues = new String[NUM_ROWS][NUM_COLS];
        numCards = gameLogic.getHockeyCardsNum();
        numFoundCards = gameLogic.getNumFoundHockeyCards();
        scansUsed = gameLogic.getScanUsed();
    }

}