package com.example.fragmentexample1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.fragmentexample1.databinding.FragmentSimpleBinding;

public class SimpleFragment extends Fragment {
    private FragmentSimpleBinding binding;
    private static final int YES = 0;
    private static final int NO = 1;
    public SimpleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSimpleBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                View radioButton = binding.radioGroup.findViewById(checkedId);
                int index = binding.radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        binding.fragmentHeader.setText(R.string.yes_message);
                        break;
                    case NO:
                        binding.fragmentHeader.setText(R.string.no_message);
                        break;
                    default:
                        break;
                }
            }
        });

    }
}