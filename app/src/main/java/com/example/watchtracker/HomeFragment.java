package com.example.watchtracker;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.

 */
public class HomeFragment extends Fragment implements MovieAdapter.OnClickListener {

    ViewPager sliderpager;
    View v;
    TabLayout indicator;
    List<Slide> lstSlides;
    Handler handler;

    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<MovieModelClass> movies = new ArrayList<>();
    private MovieAdapter moviesAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        sliderpager = v.findViewById(R.id.slider_pager)  ;
        indicator = v.findViewById(R.id.indicator);


        //prepare list of slides
        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide2));
        lstSlides.add(new Slide(R.drawable.slide1));
        lstSlides.add(new Slide(R.drawable.slide4));
        lstSlides.add(new Slide(R.drawable.slide5));

        SliderPagerAdapter adapter = new SliderPagerAdapter(getActivity(), lstSlides);

        sliderpager.setAdapter(adapter);
        indicator.setupWithViewPager(sliderpager,true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);

        recyclerView = v.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        moviesAdapter = new MovieAdapter(getContext(), movies, this);
        recyclerView.setAdapter(moviesAdapter);

        getMovies();

        return v;
    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });

        }
    }

    private void getMovies(){
        MoviesAPI apiInterface = MoviesService.createService(MoviesAPI.class);
        Call<PopularTVShows> call = apiInterface.getMovies("cbe52ac5be6d5241ac969e77bc5d8185");
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