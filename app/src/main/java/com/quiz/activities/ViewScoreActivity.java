package com.quiz.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quiz.R;
import com.quiz.adapter.ScoreAdapter;
import com.quiz.utils.AppPreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewScoreActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.listview_ViewScore)
    ListView listviewViewScore;
    ScoreAdapter scoreAdapter;
    ArrayList<String> scoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);

        initControls();

    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        scoreList = new ArrayList<>();


       // String prefScore = new Gson().toJson(scoreList);
        String prefScore = appPreference.getString(AppPreference.PREF_SCORE, "");
        if(prefScore.length() > 0){
            scoreList = new Gson().fromJson(prefScore, new TypeToken<ArrayList<String>>() { }.getType());
        }

        Collections.sort(scoreList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareToIgnoreCase(s1);
            }
        });

        scoreAdapter = new ScoreAdapter(baseActivity, scoreList);
        listviewViewScore.setAdapter(scoreAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                onBackPressed();
                break;
        }
        return (super.onOptionsItemSelected(item));
    }
}
