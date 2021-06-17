package com.example.watchtracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesAPI {
    @GET("movie/popular")
    Call<PopularTVShows> getMovies(
            @Query("api_key") String apiKey
    );

    @GET("search/movie")
    Call<PopularTVShows> searchMovies(
            @Query("api_key") String apikey,
            @Query("query") CharSequence query
    );
}
