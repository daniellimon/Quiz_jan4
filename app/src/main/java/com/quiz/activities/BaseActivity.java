package com.quiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.quiz.R;
import com.quiz.utils.AppPreference;
import com.quiz.utils.UtilityManager;

public class BaseActivity extends AppCompatActivity {

    Activity baseActivity;
    UtilityManager utilityManager;
    AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = this;
        utilityManager = new UtilityManager(baseActivity);
        appPreference = new AppPreference(baseActivity);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void navigation(Class classNavigation, boolean boolFinish) {
        Intent intent = new Intent(baseActivity, classNavigation);
        startActivity(intent);
        if(boolFinish){
            finish();
        }
    }

    public void navigation(Intent intentNavigation, boolean boolFinish) {
        startActivity(intentNavigation);
        if(boolFinish){
            finish();
        }
    }
}
