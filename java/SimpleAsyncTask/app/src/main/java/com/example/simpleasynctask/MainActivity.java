package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.simpleasynctask.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new SimpleAsyncTask(binding.text).execute();
            }
        });
        if(savedInstanceState != null){
            binding.text.setText(savedInstanceState.getString(TEXT_STATE));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, binding.text.getText().toString());
    }
}