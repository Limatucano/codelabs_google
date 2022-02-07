package com.example.droidcafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.droidcafe.databinding.FragmentSecondBinding;
import java.util.HashMap;
import java.util.Map;
import com.example.droidcafe.DatePickerFragment;

public class SecondFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentSecondBinding binding;
    private String messageReceived;
    private DatePickerFragment datePickerFragment = new DatePickerFragment();

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

        binding.alertButton.setOnClickListener(onClick -> {
            onCLickShowAlert();
        });
        binding.dateButton.setOnClickListener(onClick -> {
           showDatePicker();
        });
        binding.phoneText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;

                if(i == EditorInfo.IME_ACTION_SEND){
                    dialNumber();
                    handled = true;
                }
                return handled;
            }
        });
        binding.labelSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(),R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.labelSpinner.setAdapter(adapter);
        datePickerFragment.dateValue.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                displayToast(s);
            }
        });

    }

    public void showDatePicker(){
        assert getFragmentManager() != null;
        datePickerFragment.show(getFragmentManager(),"datePicker");
    }

    public void onCLickShowAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());

        alert.setTitle("ALERTA");
        alert.setMessage("Clique em OK para continuar ou CANCEL para parar:");
        alert.setCancelable(false);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                displayToast("OK");
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                displayToast("CANCEL");
            }
        });

        alert.show();

    }
    public void dialNumber(){
        String phoneNum = "tel:" + binding.phoneText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNum));
        startActivity(intent);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}