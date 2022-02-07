package com.example.droidcafe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.droidcafe.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private String message = "";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", message);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState != null){
            message = savedInstanceState.getString("message");
        }

        binding.fab.setOnClickListener(onClick -> {
            NavDirections action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(message);
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(action);
        });

        binding.donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = getString(R.string.donut_order_message);
                displayToast(message);
            }
        });
        binding.iceCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = getString(R.string.ice_cream_order_message);
                displayToast(message);
            }
        });
        binding.froyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             message = getString(R.string.froyo_order_message);
             displayToast(message);
            }
         });
    }

    private void displayToast(String message){
        Toast.makeText(requireActivity(),message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}