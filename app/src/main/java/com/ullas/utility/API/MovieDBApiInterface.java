package com.ullas.utility.API;

import com.ullas.CelebsFormat.Celebs;
import com.ullas.CelebsFormat.CelebsDetails;
import com.ullas.MovieFormat.MovieList;
import com.ullas.SignInPackage.AccountDetails;
import com.ullas.SignInPackage.account_access;
import com.ullas.SignInPackage.session_id;
import com.ullas.TVFormat.TVList;
import com.ullas.WatchlistFormat.PostJsonInWatchList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBApiInterface {

    @GET("/3/authentication/token/new")
    Call<account_access> getRequestToken(@Query("api_key") String key);

    @GET("/3/authentication/token/validate_with_login")
    Call<account_access> getRequestAuthenticated(@Query("api_key") String key, @Query("request_token") String request_token, @Query("username") String username, @Query("password") String password);

    @GET("/3/authentication/session/new")
    Call<session_id> getSessionID(@Query("api_key") String key, @Query("request_token") String request_token);

    @GET("/3/account")
    Call<AccountDetails> getAccountDetails(@Query("api_key") String key, @Query("session_id") String session_id);

    @GET("/3/movie/{type}")
    Call<MovieList> getMovies(@Path("type") String type, @Query("api_key") String key);

    @GET("/3/tv/{type}")
    Call<TVList> getTVShows(@Path("type") String type, @Query("api_key") String key);

    @GET("/3/person/popular")
    Call<Celebs> getPopularPerson(@Query("api_key") String key);

    @GET("/3/person/{id}")
    Call<CelebsDetails> getPersonDetails(@Path("id") String userId, @Query("api_key") String key);

    @GET("/3/account/{id}/watchlist/movies")
    Call<MovieList> getUserMovieWatchlist(@Path("id") String user_id, @Query("api_key") String key, @Query("session_id") String session_id);

    @GET("/3/account/{id}/watchlist/tv")
    Call<TVList> getUserTVShowWatchlist(@Path("id") String user_id, @Query("api_key") String key, @Query("session_id") String session_id);

    @POST("/3/account/{id}/watchlist")
    Call<PostJsonInWatchList> createJson(@Path("id") String user_id, @Query("api_key") String key, @Query("session_id") String session_id, @Body PostJsonInWatchList postJsonInWatchList);

}
