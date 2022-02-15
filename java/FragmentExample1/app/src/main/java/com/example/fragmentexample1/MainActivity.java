package com.example.fragmentexample1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fragmentexample1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    private ActivityMainBinding binding;
    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private int mRadioButtonChoice = 2;
    private float mRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                binding.showFrame.setText(R.string.close);
            }
        }

        binding.showFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFragmentDisplayed){
                    closeFragment();
                }else{
                    displayFragment();
                }
//                controlVisibilityFragment();
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
    /**
     * This method is called when the user clicks the button
     * to open the fragment.
     */
    public void displayFragment() {
        // Instantiate the fragment.
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice, mRating);
        // Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment_container,
                simpleFragment).addToBackStack(null).commit();

        // Update the Button text.
        binding.showFrame.setText(R.string.close);
        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;
    }

    /**
     * This method is called when the user clicks the button to
     * close the fragment.
     */
    public void closeFragment() {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        // Update the Button text.
        binding.showFrame.setText(R.string.open);
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
        Toast.makeText(this, "Choice is " + String.valueOf(choice), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRatingChoice(float rating) {
        mRating = rating;
        Toast.makeText(this, "Rating is " + String.valueOf(rating), Toast.LENGTH_SHORT).show();
    }
}