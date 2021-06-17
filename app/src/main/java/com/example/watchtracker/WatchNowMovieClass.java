package com.example.watchtracker;

public class WatchNowMovieClass {
    String Text;
    int Image;

    public WatchNowMovieClass(String text, int image) {
        Text = text;
        Image = image;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
