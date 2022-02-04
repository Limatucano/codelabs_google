package com.example.droidcafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.droidcafe.databinding.FragmentSecondBinding;

import java.util.HashMap;
import java.util.Map;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private String messageReceived;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            this.messageReceived = SecondFragmentArgs.fromBundle(bundle).getMessage();
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.textviewSecond.setText(this.messageReceived);

        binding.nextday.setOnClickListener(this::onRadioButtonClicked);
        binding.pickup.setOnClickListener(this::onRadioButtonClicked);

        binding.sameday.setOnClickListener(this::onRadioButtonClicked);

    }

    public void onRadioButtonClicked(View view){
        Map<Integer,String> map = new HashMap(){{
            put(R.id.sameday, getString(R.string.same_day_radio));
            put(R.id.nextday, getString(R.string.next_day_radio));
            put(R.id.pickup, getString(R.string.pick_up_radio));
        }};

        boolean isChecked = ((RadioButton) view).isChecked();
        if(isChecked){
            displayToast(map.get(view.getId()));
        }

    }
    public void displayToast(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}