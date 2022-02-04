package com.example.droidcafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.droidcafe.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

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

        binding.fab.setOnClickListener(onClick -> {
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });

        binding.donut.setOnClickListener(onClick ->{
            displayToast(getString(R.string.donut_order_message));
        });
        binding.iceCream.setOnClickListener(onClick ->{
           displayToast(getString(R.string.ice_cream_order_message));
        });
        binding.froyo.setOnClickListener(onClick ->{
           displayToast(getString(R.string.froyo_order_message));
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