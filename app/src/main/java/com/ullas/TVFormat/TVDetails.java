package com.ullas.TVFormat;

import java.io.Serializable;


public class TVDetails implements Serializable{

    int id;
    String poster_path;
    String popularity;
    String backdrop_path;
    String vote_average;
    String overview;
    String first_air_date;
    String original_language;
    String vote_count;
    String name;

    public String getPosterPath() {
        return poster_path;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstAirDate() {
        return first_air_date;
    }
}
