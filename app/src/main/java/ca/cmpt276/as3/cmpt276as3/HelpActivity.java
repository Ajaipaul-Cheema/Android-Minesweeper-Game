package ca.cmpt276.as3.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityHelpBinding;

public class HelpActivity extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, HelpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ca.cmpt276.as3.cmpt276as3.databinding.ActivityHelpBinding binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        launchCitations();

    }

    private void launchCitations() {
        Button launchCitations = findViewById(R.id.citationsButton);
        launchCitations.setOnClickListener(v -> {
            Intent i = CitationsActivity.makeLaunchIntent(HelpActivity.this);
            startActivity(i);
        });
    }
}