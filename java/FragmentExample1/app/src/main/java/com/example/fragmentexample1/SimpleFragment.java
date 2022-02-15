package com.example.fragmentexample1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.fragmentexample1.databinding.FragmentSimpleBinding;

public class SimpleFragment extends Fragment {
    private FragmentSimpleBinding binding;
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE = 2;
    public int mRadioButtonChoice = NONE;
    public float mRating = 0;
    OnFragmentInteractionListener mListener;
    private static final String CHOICE = "choice";
    private static final String RATING = "rating";
    public SimpleFragment() {
    }
    public static SimpleFragment newInstance(int choice, float rating){
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE, choice);
        arguments.putFloat(RATING,rating);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            throw new ClassCastException(context.toString() + getResources().getString(R.string.exception_message));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSimpleBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mRadioButtonChoice = getArguments().getInt(CHOICE);
        if(mRadioButtonChoice != NONE){
            binding.radioGroup.check(binding.radioGroup.getChildAt(mRadioButtonChoice).getId());
        }
        mRating = getArguments().getFloat(RATING);
        binding.ratingSong.setRating(mRating);

        binding.ratingSong.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(requireActivity(),String.valueOf(v),Toast.LENGTH_SHORT).show();
                mListener.onRatingChoice(v);
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                View radioButton = binding.radioGroup.findViewById(checkedId);
                int index = binding.radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        binding.fragmentHeader.setText(R.string.yes_message);
                        mRadioButtonChoice = YES;
                        mListener.onRadioButtonChoice(YES);
                        break;
                    case NO:
                        binding.fragmentHeader.setText(R.string.no_message);
                        mRadioButtonChoice = NO;
                        mListener.onRadioButtonChoice(NO);
                        break;
                    default:
                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice(NONE);
                        break;
                }
            }
        });

    }
}

