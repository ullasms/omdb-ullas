package com.ullas.MovieFormat;

import java.io.Serializable;
import java.util.ArrayList;


public class MovieList implements Serializable {
    public int page;
    public ArrayList<MovieDetails> results;
    int total_pages;
    int total_results;
}
