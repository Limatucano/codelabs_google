package com.example.fragmentexample1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.fragmentexample1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.showFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlVisibilityFragment();
            }
        });
    }

    private void controlVisibilityFragment(){
        SimpleFragment simpleFragment = new SimpleFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(binding.showFrame.getText() == getString(R.string.close)){
            SimpleFragment fragmentView = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
            fragmentTransaction.remove(fragmentView).commit();
            binding.showFrame.setText(R.string.open);
        }else{
            fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();
            binding.showFrame.setText(R.string.close);
        }


    }
}