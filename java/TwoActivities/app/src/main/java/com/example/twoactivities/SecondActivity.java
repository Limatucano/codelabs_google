package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.twoactivities.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.twoactivities.extra.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding viewBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        viewBinding.textMessage.setText(message);

        viewBinding.buttonReply.setOnClickListener(onClick -> {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(EXTRA_REPLY, viewBinding.editTextSecond.getText().toString());
            setResult(RESULT_OK, replyIntent);
            finish();
        });
    }
}