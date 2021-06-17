package com.example.watchtracker;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PopularTVShows {

    @SerializedName("results")
    List<MovieModelClass> results = new ArrayList<MovieModelClass>();

    public List<MovieModelClass> getResults() {
        return results;
    }

    public void setResults(List<MovieModelClass> results) {
        this.results = results;
    }
}
