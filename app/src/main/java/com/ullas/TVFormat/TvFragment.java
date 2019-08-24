package com.ullas.TVFormat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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


public class TvFragment extends Fragment implements Constants{

    boolean b1, b2, paused;
    ProgressDialog pDialog;
    SharedPreferencesUtils spUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tv_fragment, container, false);

        spUtils = new SharedPreferencesUtils(getActivity());
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        paused = b1 = b2 = false;

        return view;
    }

    @Override
    public void onResume() {
        ConnectionDetector cd = new ConnectionDetector(getActivity());
        if (!cd.isConnectingToInternet()) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), NoInternetActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        pDialog.show();
        paused = false;

        APICalls.getTVShows("popular", getContext(), pDialog, new APICalls.TVShowsCallbackListener() {
            @Override
            public void onSuccessfulTVShow(TVList tvList) {
                Bundle b = new Bundle();
                TVLinearLayoutFragment tvf = new TVLinearLayoutFragment();
                b.putSerializable(Constants.ALL_TV_SHOW_DETAILS, tvList.results);
                tvf.setArguments(b);
                if(!paused)
                    getFragmentManager().beginTransaction().replace(R.id.id_PopularTvShows, tvf).commit();
                b1 = true;
                if(b2) pDialog.dismiss();
            }
        });

        APICalls.getTVShows("top_rated", getContext(), pDialog, new APICalls.TVShowsCallbackListener() {
            @Override
            public void onSuccessfulTVShow(TVList tvList) {
                TVLinearLayoutFragment tvf = new TVLinearLayoutFragment();
                Bundle b = new Bundle();
                b.putSerializable(Constants.ALL_TV_SHOW_DETAILS, tvList.results);
                tvf.setArguments(b);
                if(!paused)
                    getFragmentManager().beginTransaction().replace(R.id.id_MostRatedTvShows, tvf).commit();
                b2 = true;
                if(b1)
                    pDialog.dismiss();
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
}
