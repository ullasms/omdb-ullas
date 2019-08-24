package com.ullas.MovieFormat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ullas.BuildConfig;
import com.ullas.Constants;
import com.ullas.utility.API.APICalls;
import com.ullas.utility.NoInternetActivity;
import com.ullas.R;
import com.ullas.utility.ConnectionDetector;
import com.ullas.utility.API.MovieDBApiClient;
import com.ullas.utility.SharedPreferencesUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieFragment extends Fragment implements Constants{

    View view;
    boolean b1, b2, paused;
    ProgressDialog pDialog;
    SharedPreferencesUtils spUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_movie_fragment, container, false);
        paused = b1 = b2 = false;
        spUtils = new SharedPreferencesUtils(getActivity());

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");

        return view;
    }
    @Override
    public void onResume() {
        checkConnectivity();
        pDialog.show();
        paused = false;

        APICalls.getMovies("top_rated", getContext(), pDialog, new APICalls.MovieCallbackListener() {
            @Override
            public void onSuccessfulMovie(MovieList movie) {
                MovieLinearLayoutFragment mf = new MovieLinearLayoutFragment();
                Bundle b = new Bundle();
                b.putSerializable(ALL_MOVIE_DETAILS, movie.results);
                mf.setArguments(b);
                if(paused == false)
                    getFragmentManager().beginTransaction().replace(R.id.frameLayoutTopRatedMovies, mf).commit();
                b1 = true;
                if(b2) pDialog.dismiss();
            }
        });

        APICalls.getMovies("popular", getContext(), pDialog, new APICalls.MovieCallbackListener() {
            @Override
            public void onSuccessfulMovie(MovieList movie) {
                MovieLinearLayoutFragment mf = new MovieLinearLayoutFragment();
                Bundle b = new Bundle();
                b.putSerializable(ALL_MOVIE_DETAILS, movie.results);
                mf.setArguments(b);
                if(paused == false)
                    getFragmentManager().beginTransaction().replace(R.id.frameLayoutPopularMovies, mf).commit();
                b2 = true;
                if(b1) pDialog.dismiss();
            }
        });
        super.onResume();
    }

    @Override
    public void onPause() {
        if(pDialog.isShowing())
            pDialog.dismiss();
        super.onPause();
    }

    private void checkConnectivity() {
        ConnectionDetector cd = new ConnectionDetector(getActivity());
        if (!cd.isConnectingToInternet()) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), NoInternetActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}
