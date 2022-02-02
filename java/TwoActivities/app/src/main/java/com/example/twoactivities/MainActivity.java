package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    public ActivityMainBinding viewBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.buttonSend.setOnClickListener(onClick -> {

            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra(EXTRA_MESSAGE, viewBinding.editTextMain.getText().toString());
            startActivityForResult(intent,TEXT_REQUEST);
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                viewBinding.textHeaderReply.setVisibility(View.VISIBLE);
                viewBinding.textMessageReply.setText(reply);
                viewBinding.textMessageReply.setVisibility(View.VISIBLE);
            }
        }
    }
}