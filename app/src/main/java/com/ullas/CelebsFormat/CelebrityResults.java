package com.ullas.CelebsFormat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class CelebrityResults implements Serializable {

    String profile_path;
    String id;
    String name;
    @SerializedName("known_for")
    ArrayList<Popularity> moviesList;

    public String getProfilePath() {
        return profile_path;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMovies(){
        return moviesList.get(0).getOriginal_title() + "," + moviesList.get(1).getOriginal_title() + "," +
                moviesList.get(2).getOriginal_title();
    }
}
