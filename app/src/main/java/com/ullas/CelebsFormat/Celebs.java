package com.ullas.CelebsFormat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Celebs implements Serializable{

    int page;
    @SerializedName("results")
    private ArrayList<CelebrityResults> celebsList;

    public ArrayList<CelebrityResults> getCelebsList() {
        return celebsList;
    }
}
