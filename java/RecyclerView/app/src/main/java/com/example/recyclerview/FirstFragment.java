package com.example.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recyclerview.databinding.FragmentFirstBinding;

import java.util.LinkedList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private WordListAdapter mAdapter;
    private MutableLiveData<LinkedList<String>> mWordListMutable = new MutableLiveData<LinkedList<String>>();
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

        generateList();

        this.mWordListMutable.observe(requireActivity(), new Observer<LinkedList<String>>() {
            @Override
            public void onChanged(LinkedList<String> mWords) {
                Log.d("TESTE", mWords.toString());
                setValuesToRecyclerView(mWords);
            }
        });


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

    }
    public void generateList(){
        LinkedList<String> mWordList = new LinkedList<>();
        for (int i = 0; i<20; i++){
            mWordList.addLast("Word" + i);
        }
        this.mWordListMutable.postValue(mWordList);
    }
    public void setValuesToRecyclerView(LinkedList<String> mWordList){
        mAdapter = new WordListAdapter(requireContext(), mWordList);
        binding.recyclerview.setAdapter(mAdapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_reset) {
            generateList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}