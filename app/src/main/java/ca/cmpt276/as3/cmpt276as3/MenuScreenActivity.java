package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityMenuScreenBinding;
import ca.cmpt276.as3.model.GameLogic;

public class MenuScreenActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMenuScreenBinding binding;
    private GameLogic gameLogic;


    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, MenuScreenActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        gameLogic = GameLogic.getInstance();
        launchingGame();
        launchingHelp();
        launchingOptions();

    }

    private void launchingGame() {
        Button launchGame = findViewById(R.id.playGame);
        launchGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = GameActivity.makeLaunchIntent(MenuScreenActivity.this);
                startActivity(i);
            }
        });
    }

    private void launchingHelp() {
        Button launchHelp = findViewById(R.id.help);
        launchHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = HelpActivity.makeLaunchIntent(MenuScreenActivity.this);
                startActivity(i);
            }
        });
    }

    private void launchingOptions() {
        Button launchOptions = findViewById(R.id.options);
        launchOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OptionsActivity.makeLaunchIntent(MenuScreenActivity.this);
                startActivity(i);
            }
        });
    }
}