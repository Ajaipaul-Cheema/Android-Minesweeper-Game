package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityCitationsBinding;

public class CitationsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCitationsBinding binding;
    TextView linkButton1, linkButton2, linkButton3, linkButton4, linkButton5, linkButton6, linkButton7, linkButton8;

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, CitationsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCitationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setupHyperlinks();

    }

    private void setupHyperlinks() {
        linkButton1 = findViewById(R.id.firstImage);
        linkButton1.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton1.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.beckett.com/news/2019-20-hockey-cards-release-dates-checklists-and-set-information/"));
            startActivity(browserIntent);
        });

        linkButton2 = findViewById(R.id.secondImage);
        linkButton2.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton2.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wallpapercave.com/hockey-rink-wallpapers"));
            startActivity(browserIntent);
        });

        linkButton3 = findViewById(R.id.thirdImage);
        linkButton3.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton3.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://freesvg.org/hockey-rink-vector-clip-art"));
            startActivity(browserIntent);
        });

        linkButton4 = findViewById(R.id.fourthImage);
        linkButton4.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton4.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://freesvg.org/red-button-with-word-help"));
            startActivity(browserIntent);
        });

        linkButton5 = findViewById(R.id.fifthImage);
        linkButton5.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton5.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://freesvg.org/vector-clip-art-of-grayscale-settings-options-icon"));
            startActivity(browserIntent);
        });

        linkButton6 = findViewById(R.id.sixthImage);
        linkButton6.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton6.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.happywall.com/wall-murals/ice-hockey-game-wallpaper"));
            startActivity(browserIntent);
        });

        linkButton7 = findViewById(R.id.seventhImage);
        linkButton7.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton7.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.reddit.com/r/leafs/comments/asq8cj/hockey_night_in_toronto/"));
            startActivity(browserIntent);
        });

        linkButton8 = findViewById(R.id.eighthImage);
        linkButton8.setTextColor(ContextCompat.getColor(this, R.color.black));
        linkButton8.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.istockphoto.com/vector/ice-hockey-championship-neon-sign-with-goalkeeper-ice-hockey-competition-logo-gm1209530517-350045687"));
            startActivity(browserIntent);
        });



    };


}