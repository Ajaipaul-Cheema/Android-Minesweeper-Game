package ca.cmpt276.as3.cmpt276as3;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityMainBinding;
import ca.cmpt276.as3.cmpt276as3.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Handler handler;

    Runnable run = () -> {
        Intent i = MenuScreenActivity.makeLaunchIntent(MainActivity.this);
        startActivity(i);
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        handler = new Handler();
        handler.postDelayed(run, 8000);

        slide_down();
        rotate();
        goToMainMenu();
    }


    private void slide_down() {
        TextView gameTitle = findViewById(R.id.titleMainScreen);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_down);
        gameTitle.startAnimation(animation);
    }

    private void rotate() {
        Button menuBtn = (Button) findViewById(R.id.menuButton);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
        menuBtn.startAnimation(animation);
    }

    private void goToMainMenu() {
        Button launchMenu = findViewById(R.id.menuButton);
        launchMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MenuScreenActivity.makeLaunchIntent(MainActivity.this);
                startActivity(i);
                handler.removeCallbacks(run);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}