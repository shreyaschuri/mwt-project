package com.example.watchtracker;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchNowFragment extends Fragment {

    View v;
    RecyclerView recyclerView;


    MovieAdapter movieAdapter;
    FirebaseRecyclerOptions<MovieModelClass> mList;

    public WatchNowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_watch_now, container, false);

        recyclerView=v.findViewById(R.id.recyclerViewId);
        movieAdapter = new WatchNowAdapter(mList, getContext());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<MovieModelClass> mList =
                new FirebaseRecyclerOptions.Builder<MovieModelClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Watchnow"), MovieModelClass.class)
                        .build();


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}