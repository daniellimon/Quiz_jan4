package com.quiz.activities;

import android.content.Intent;
import android.os.Bundle;

import com.quiz.utils.AppPreference;

public class SplashActivity extends BaseActivity {

    boolean boolRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

        //Read data from shared pref
        boolRegister = appPreference.getBoolean(AppPreference.PREF_REGISTER, false);
        if(boolRegister){
            navigation(MainActivity.class, true);
        }
        else{
            navigation(RegisterActivity.class, true);
        }
    }
}
