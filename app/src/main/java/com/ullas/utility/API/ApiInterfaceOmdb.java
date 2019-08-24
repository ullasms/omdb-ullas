package com.ullas.utility.API;

import com.ullas.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterfaceOmdb {

    // In query it will append ? than parameter than equal to by itself.
    // Url request to OMDB Api for searching movie
    @GET(".")
    Call<Search> getMySearch(@Query("apikey") String apiKey, @Query("t") String item);

}
