package com.quiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.quiz.R;
import com.quiz.model.Quiz;
import com.quiz.utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.btnStartQuiz)
    AppCompatButton btnStartQuiz;
    @Bind(R.id.btnViewQuestion)
    AppCompatButton btnViewQuestion;
    @Bind(R.id.btnViewScores)
    AppCompatButton btnViewScores;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    ArrayList<Quiz> quizList;
    @Bind(R.id.textMessage)
    TextView textMessage;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initControls();

    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        userName = appPreference.getString(AppPreference.PREF_USERNAME, "");
        textMessage.setText("Welcome to the UCL Quiz, "+userName);
        quizList = new ArrayList<>();

        Quiz quiz = new Quiz();
        quiz.setQuestion("When was UCL founded?");
        List<String> answserList = new ArrayList<>();
        answserList.add("1900");
        answserList.add("1826");
        answserList.add("1724");
        quiz.setListOptions(answserList);
        quiz.setAnswer(1);
        quizList.add(quiz);

        quiz = new Quiz();
        quiz.setQuestion("How many nationalities do UCL students represent?");
        answserList = new ArrayList<>();
        answserList.add("150");
        answserList.add("30");
        answserList.add("70");
        quiz.setListOptions(answserList);
        quiz.setAnswer(0);
        quizList.add(quiz);

        quiz = new Quiz();
        quiz.setQuestion("How many students attend UCL?");
        answserList = new ArrayList<>();
        answserList.add("20,000");
        answserList.add("30,000");
        answserList.add("40,000");
        quiz.setListOptions(answserList);
        quiz.setAnswer(1);
        quizList.add(quiz);

        quiz = new Quiz();
        quiz.setQuestion("Where was UCL ranked in the QS ratings for 2015");
        answserList = new ArrayList<>();
        answserList.add("20th");
        answserList.add("7th");
        answserList.add("1st");
        quiz.setListOptions(answserList);
        quiz.setAnswer(1);
        quizList.add(quiz);

        quiz = new Quiz();
        quiz.setQuestion("How many Nobel prizes have been awarded to UCL students?");
        answserList = new ArrayList<>();
        answserList.add("3");
        answserList.add("50");
        answserList.add("29");
        quiz.setListOptions(answserList);
        quiz.setAnswer(2);
        quizList.add(quiz);



    }



    @OnClick({R.id.btnStartQuiz, R.id.btnViewQuestion, R.id.btnViewScores})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartQuiz:
                Intent intentStartQuiz = new Intent(baseActivity, StartQuizActivity.class);
                intentStartQuiz.putExtra("Question", quizList);
                navigation(intentStartQuiz, false);
                break;
            case R.id.btnViewQuestion:
                Intent intentViewQuestion = new Intent(baseActivity, ViewQuestionActivity.class);
                intentViewQuestion.putExtra("Question", quizList);
                navigation(intentViewQuestion, false);
                break;
            case R.id.btnViewScores:
                navigation(ViewScoreActivity.class, false);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
