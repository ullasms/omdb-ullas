package com.ullas.CelebsFormat;

import java.io.Serializable;

public class Popularity implements Serializable{
    String poster_path;
    String overview;
    String release_date;
    String original_title;
    int id;
    String backdrop_path;

    public String getOriginal_title() {
        return original_title;
    }
}
