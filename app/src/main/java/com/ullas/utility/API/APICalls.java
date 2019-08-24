package com.ullas.utility.API;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.ullas.BuildConfig;
import com.ullas.MovieFormat.MovieList;
import com.ullas.TVFormat.TVList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class APICalls {

    public static void getMovies(String path, final Context context, final ProgressDialog pDialog,
                                 final MovieCallbackListener listener){
        Call<MovieList> movieListCall = MovieDBApiClient.getInterface().getMovies(path, BuildConfig.MOVIE_DB_API_KEY);
        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessfulMovie(response.body());
                } else {
                    pDialog.dismiss();
                    Toast.makeText(context, response.code() + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                pDialog.dismiss();
               // Toast.makeText(context, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getTVShows(String path, final Context context, final ProgressDialog pDialog,
                                 final TVShowsCallbackListener listener){
        Call<TVList> tvListCall = MovieDBApiClient.getInterface().getTVShows(path, BuildConfig.MOVIE_DB_API_KEY);
        tvListCall.enqueue(new Callback<TVList>() {
            @Override
            public void onResponse(Call<TVList> call, Response<TVList> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessfulTVShow(response.body());
                } else {
                    pDialog.dismiss();
                    Toast.makeText(context, response.code() + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TVList> call, Throwable t) {
                pDialog.dismiss();
              //  Toast.makeText(context, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface MovieCallbackListener{
        void onSuccessfulMovie(MovieList movieList);
    }

    public interface TVShowsCallbackListener{
        void onSuccessfulTVShow(TVList tvList);
    }

}
