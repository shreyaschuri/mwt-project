package com.example.watchtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchLaterFragment extends Fragment {

    View v;
    RecyclerView recyclerView;

    List<WatchNowMovieClass> mList;
    WatchNowAdapter watchNowAdapter;

    public WatchLaterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v= inflater.inflate(R.layout.fragment_watch_now, container, false);

        recyclerView=v.findViewById(R.id.recyclerViewId);
        watchNowAdapter = new WatchNowAdapter(mList, getContext());
        recyclerView.setAdapter(watchNowAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerOptions<MovieModelClass> mList =
                new FirebaseRecyclerOptions.Builder<MovieModelClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Watchlater"), MovieModelClass.class)
                        .build();

        return v;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}