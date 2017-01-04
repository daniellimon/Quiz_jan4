package com.quiz.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quiz.R;
import com.quiz.model.Quiz;
import com.quiz.utils.AppPreference;
import com.quiz.utils.UtilityManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartQuizActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textQustion)
    TextView textQustion;
    ArrayList<Quiz> quizList;
    @Bind(R.id.radioA)
    RadioButton radioA;
    @Bind(R.id.radioB)
    RadioButton radioB;
    @Bind(R.id.radioC)
    RadioButton radioC;
    @Bind(R.id.rbgOptions)
    RadioGroup rbgOptions;
    @Bind(R.id.btnCheat)
    AppCompatButton btnCheat;
    @Bind(R.id.btnSubmit)
    AppCompatButton btnSubmit;

    ArrayList<String> scoreList;
    int question = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            quizList = (ArrayList<Quiz>) bundle.getSerializable("Question");
        }

        initControls();
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        scoreList = new ArrayList<>();

        String prefScore = appPreference.getString(AppPreference.PREF_SCORE, "");
        if(prefScore.length() > 0){
            scoreList = new Gson().fromJson(prefScore, new TypeToken<ArrayList<String>>() { }.getType());
        }

        bindQuiz(question);

    }

    private void bindQuiz(int question) {
        rbgOptions.clearCheck();

        Quiz quiz = quizList.get(question);
        textQustion.setText(quiz.getQuestion());
        radioA.setText(quiz.getListOptions().get(0));
        radioB.setText(quiz.getListOptions().get(1));
        radioC.setText(quiz.getListOptions().get(2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(quizList.size() > 1){
            getMenuInflater().inflate(R.menu.menu_skip, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_skip:
                requestNextQuiz();
                break;
        }
        return (super.onOptionsItemSelected(item));
    }

    @OnClick({R.id.btnCheat, R.id.btnSubmit})
    public void onClick(View view) {
        int checkAns = quizList.get(question).getAnswer();
        String message = "";
        switch (view.getId()) {
            case R.id.btnCheat:

                message = "The correct answer is "+quizList.get(question).getListOptions().get(checkAns)+".";

                utilityManager.showAlertDialog("Cheat", message, "Ok", new UtilityManager.AlertDialogListener() {
                    @Override
                    public void onPositiveButtonClick(DialogInterface dialog, int which) {
                        requestNextQuiz();
                    }
                }, false);
                break;
            case R.id.btnSubmit:
                int answer = -1;
                if(rbgOptions.getCheckedRadioButtonId()  == R.id.radioA){
                    Log.e("Option", "A");
                    answer = 0;
                }
                else if(rbgOptions.getCheckedRadioButtonId()  == R.id.radioB){
                    Log.e("Option", "B");
                    answer = 1;
                }
                else if(rbgOptions.getCheckedRadioButtonId()  == R.id.radioC){
                    Log.e("Option", "C");
                    answer = 2;
                }

                if(answer != -1){

                    if(checkAns == answer){
                        score++;
                        message = "Your answer is correct.";
                    }
                    else{
                        message = "Your answer is wrong!\nCorrect answer is "+quizList.get(question).getListOptions().get(checkAns)+".";
                    }

                    utilityManager.showAlertDialog("Result", message, "Ok", new UtilityManager.AlertDialogListener() {
                        @Override
                        public void onPositiveButtonClick(DialogInterface dialog, int which) {
                            requestNextQuiz();
                        }
                    }, false);

                }
                else{
                    utilityManager.showMessage("Please select an answer first!");
                }


                break;
        }
    }

    private void requestNextQuiz() {
        if(quizList.size()==1){
            onBackPressed();
        }
        else{
            question++;
            if(question > quizList.size()-1){

                scoreList.add(score+"/"+quizList.size());
                String prefScore = new Gson().toJson(scoreList);
                appPreference.setString(AppPreference.PREF_SCORE, prefScore);

                utilityManager.showAlertDialog("The quiz is over!", "Your score was "+score+"/"+quizList.size()+".", "Ok", new UtilityManager.AlertDialogListener() {
                    @Override
                    public void onPositiveButtonClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                }, false);
            }
            else{
                bindQuiz(question);
            }
        }

    }

    @Override
    public void onBackPressed() {
        if(quizList.size()==1 || question > quizList.size()-1){
            finish();
        }
        else{
            utilityManager.showMessage("Complete the quiz before quitting!");
        }

    }
}
