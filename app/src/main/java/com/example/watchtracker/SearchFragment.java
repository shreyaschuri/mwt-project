package com.example.watchtracker;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements MovieAdapter.OnClickListener{

    View v;
    Handler handler;

    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<MovieModelClass> movies = new ArrayList<>();
    private MovieAdapter moviesAdapter;
    private SearchView searchView;

    CharSequence queryText;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = v.findViewById(R.id.recyclerview1);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        moviesAdapter = new MovieAdapter(getContext(), movies, this);
        recyclerView.setAdapter(moviesAdapter);

        searchView = v.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryText  = searchView.getQuery();
                searchMovies();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return v;
    }


    private void searchMovies(){
        MoviesAPI apiInterface = MoviesService.createService(MoviesAPI.class);
        Call<PopularTVShows> call = apiInterface.searchMovies("cbe52ac5be6d5241ac969e77bc5d8185", queryText);
        call.enqueue(new Callback<PopularTVShows>() {
            @Override
            public void onResponse(Call<PopularTVShows> call, Response<PopularTVShows> response) {
                if(response.isSuccessful()) {
                    List<MovieModelClass> results = fetchResults(response);
                    movies.addAll(results);
                    moviesAdapter.notifyDataSetChanged();

                }else{
                    Log.e(TAG, response.message());
                }
            }
            @Override
            public void onFailure(Call<PopularTVShows> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private List<MovieModelClass> fetchResults(Response<PopularTVShows> response){
        PopularTVShows popularTVShows = response.body();
        return popularTVShows.getResults();
    }

    @Override
    public void onItemClick(int position) {

        Bundle bundle = new Bundle();
        bundle.putString("Title", movies.get(position).getTitle());
        bundle.putString("VoteAverage", movies.get(position).getVote_average());
        bundle.putString("Overview", movies.get(position).getOverview());
        bundle.putString("ImgUrl", movies.get(position).getImg());
        MoviePage moviePage = new MoviePage();
        moviePage.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId, moviePage);
        fragmentTransaction.addToBackStack(getTag());
        fragmentTransaction.commit();

    }
}