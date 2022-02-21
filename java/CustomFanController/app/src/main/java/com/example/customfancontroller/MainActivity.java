package com.example.customfancontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialView dialView = (DialView) findViewById(R.id.dialView);
        EditText quantity = (EditText) findViewById(R.id.quantity);
        Button buttonQuantity = (Button) findViewById(R.id.buttonQuantity);

        buttonQuantity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String value = quantity.getText().toString();
                dialView.setSelectionCount(Integer.parseInt(value));
            }
        });


    }
}