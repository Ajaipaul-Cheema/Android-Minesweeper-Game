package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityHelpBinding;

/**
 * This class displays how to play the game, author's details
 * and provides a button to go to all citations
 */
public class HelpActivity extends AppCompatActivity {

    TextView link;

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, HelpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ca.cmpt276.as3.cmpt276as3.databinding.ActivityHelpBinding binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        showHyperLink();
        launchCitations();

    }

    private void launchCitations() {
        Button launchCitations = findViewById(R.id.citationsButton);
        launchCitations.setOnClickListener(v -> {
            Intent i = CitationsActivity.makeLaunchIntent(HelpActivity.this);
            startActivity(i);
        });
    }

    private void showHyperLink() {
        link = findViewById(R.id.cmpt276HomePage);
        link.setTextColor(ContextCompat.getColor(this, R.color.backgroundColor));
        link.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.cmpt276HomePageLink)));
            startActivity(browserIntent);
        });
    }
}
