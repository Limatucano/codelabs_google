package com.example.scorekepper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.scorekepper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState != null) {
            updateScore(binding.score1, savedInstanceState.getString(STATE_SCORE_1));
            updateScore(binding.score2, savedInstanceState.getString(STATE_SCORE_2));
        }

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.decreaseTeam1.setOnClickListener(view ->{
            viewModel.decreaseScore(view).observe(this, score -> {
                updateScore(binding.score1, score.toString());
            });
        });

        binding.decreaseTeam2.setOnClickListener(view ->{
           viewModel.decreaseScore(view).observe(this, score -> {
               updateScore(binding.score2, score.toString());
           });
        });

        binding.increaseTeam1.setOnClickListener(view -> {
           viewModel.increaseScore(view).observe(this, score -> {
               updateScore(binding.score1, score.toString());
           });
        });

        binding.increaseTeam2.setOnClickListener(view ->{
           viewModel.increaseScore(view).observe(this, score -> {
               updateScore(binding.score2, score.toString());
           });
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the scores.
        outState.putString(STATE_SCORE_1, binding.score1.getText().toString());
        outState.putString(STATE_SCORE_2, binding.score2.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.night_mode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
        return true;
    }

    private void updateScore(TextView view, String score){
        view.setText(score);
    }
}