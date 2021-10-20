package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import androidx.core.content.ContextCompat;


import ca.cmpt276.as3.cmpt276as3.databinding.ActivityCitationsBinding;

/**
 * This class displays hyperlinks of all images and sounds
 */
public class CitationsActivity extends AppCompatActivity {


    TextView startUpImage, mainMenuImage, playGameButtonImage, helpButtonImage, optionsButtonImage, helpScreenImage, citationsScreenImage, appIconImage, scanGameSound, hockeyCardFoundSound;

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, CitationsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ca.cmpt276.as3.cmpt276as3.databinding.ActivityCitationsBinding binding = ActivityCitationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        startUpImage = findViewById(R.id.firstImage);
        mainMenuImage = findViewById(R.id.secondImage);
        playGameButtonImage = findViewById(R.id.thirdImage);
        helpButtonImage = findViewById(R.id.fourthImage);
        optionsButtonImage = findViewById(R.id.fifthImage);
        helpScreenImage = findViewById(R.id.sixthImage);
        citationsScreenImage = findViewById(R.id.seventhImage);
        appIconImage = findViewById(R.id.eighthImage);
        scanGameSound = findViewById(R.id.scanGameSound);
        hockeyCardFoundSound = findViewById(R.id.hockeyCardFoundSound);

        setupHyperlink(startUpImage, getString(R.string.startUpImageLink));
        setupHyperlink(mainMenuImage, getString(R.string.mainMenuImageLink));
        setupHyperlink(playGameButtonImage, getString(R.string.playButtonImageLink));
        setupHyperlink(helpButtonImage, getString(R.string.helpButtonImageLink));
        setupHyperlink(optionsButtonImage, getString(R.string.optionsButtonImageLink));
        setupHyperlink(helpScreenImage, getString(R.string.helpScreenImageLink));
        setupHyperlink(citationsScreenImage, getString(R.string.citationsScreenImageLink));
        setupHyperlink(appIconImage, getString(R.string.appIconImageLink));
        setupHyperlink(scanGameSound, getString(R.string.scanGameSoundLink));
        setupHyperlink(hockeyCardFoundSound, getString(R.string.foundCardSoundLink));
    }

    private void setupHyperlink(TextView textView, String url) {
        textView.setTextColor(ContextCompat.getColor(this, R.color.black));
        textView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }
}