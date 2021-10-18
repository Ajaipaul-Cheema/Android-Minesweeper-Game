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

import ca.cmpt276.as3.cmpt276as3.databinding.ActivityHelpBinding;

public class HelpActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHelpBinding binding;

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, HelpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        launchCitations();

    }

    private void launchCitations() {
        Button launchCitations = findViewById(R.id.citationsButton);
        launchCitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CitationsActivity.makeLaunchIntent(HelpActivity.this);
                startActivity(i);
            }
        });
    }
}