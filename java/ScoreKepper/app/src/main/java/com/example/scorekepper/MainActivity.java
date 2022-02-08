package com.example.scorekepper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.scorekepper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

    private void updateScore(TextView view, String score){
        view.setText(score);
    }
}