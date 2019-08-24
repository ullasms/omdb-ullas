package com.ullas.TVFormat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class TVList implements Serializable {
    public int page;
    @SerializedName("results")
    public ArrayList<TVDetails> results;
}
