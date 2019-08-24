package com.ullas.utility;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ullas.Constants;
import com.ullas.HomeActivity;
import com.ullas.R;
import com.ullas.SignInPackage.SignInScreen;

public class NoInternetActivity extends AppCompatActivity {

    Button retryButton;
    SharedPreferencesUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        spUtils = new SharedPreferencesUtils(NoInternetActivity.this);
        retryButton = (Button) findViewById(R.id.retry_button);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector cd = new ConnectionDetector(NoInternetActivity.this);
                if (cd.isConnectingToInternet()) {
                    Intent intent = new Intent();
                    intent.setClass(NoInternetActivity.this, HomeActivity.class);
                    if(spUtils.isSignedIn()){
                        intent.setClass(NoInternetActivity.this, HomeActivity.class);
                    }
                    else{
                        intent.setClass(NoInternetActivity.this, SignInScreen.class);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(NoInternetActivity.this, "Sorry no internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
