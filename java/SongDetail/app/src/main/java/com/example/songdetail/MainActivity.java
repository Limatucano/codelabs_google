package com.example.songdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.songdetail.content.SongUtils;
import com.example.songdetail.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        //setSupportActionBar(binding.toolbar);
        RecyclerView recyclerView = findViewById(R.id.song_list);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));


    }
    /**
     * The ReyclerView for the song list.
     */
    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<SongUtils.Song> mValues;

        SimpleItemRecyclerViewAdapter(List<SongUtils.Song> items) {
            mValues = items;
        }

        /**
         * This method inflates the layout for the song list.
         * @param parent ViewGroup into which the new view will be added.
         * @param viewType The view type of the new View.
         * @return
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.song_list_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * This method implements a listener with setOnClickListener().
         * When the user taps a song title, the code checks if mTwoPane
         * is true, and if so uses a fragment to show the song detail.
         * If mTwoPane is not true, it starts SongDetailActivity
         * using an intent with extra data about which song title was selected.
         *
         * @param holder   ViewHolder
         * @param position Position of the song in the array.
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(position + 1));
            holder.mContentView.setText(mValues.get(position).song_title);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context,
                            SongDetailActivity.class);
                    intent.putExtra(SongUtils.SONG_ID_KEY,
                            holder.getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }

        /**
         * Get the count of song list items.
         * @return
         */
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * ViewHolder describes an item view and metadata about its place
         * within the RecyclerView.
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            SongUtils.Song mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}