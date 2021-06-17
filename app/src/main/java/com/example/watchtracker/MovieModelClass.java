package com.example.watchtracker;

import com.google.gson.annotations.SerializedName;

public class MovieModelClass {
    @SerializedName("title")
    String title;
    @SerializedName("vote_average")
    String vote_average;
    @SerializedName("poster_path")
    String img;
    @SerializedName("overview")
    String overview;

    public MovieModelClass(String title, String vote_average, String img, String overview) {
        this.title = title;
        this.vote_average = vote_average;
        this.img = img;
        this.overview = overview;
    }

    public MovieModelClass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
