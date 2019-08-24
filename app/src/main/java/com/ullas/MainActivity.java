package com.ullas;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ullas.SignInPackage.SignInScreen;
import com.ullas.utility.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    SharedPreferencesUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spUtils = new SharedPreferencesUtils(MainActivity.this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//This is to hide notification bar from the splash screen

        //In manifest hide the action bar from the main activity so that splash screen appears properly
        final int SPLASH_TIME_OUT=3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                //If already signed in than open the HomeActivity else open the SignInScreen


                    // set the new task and clear flags
                    spUtils.setSessionID("tt3896198");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setClass(MainActivity.this, HomeActivity.class);


                startActivity(intent);

                overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}