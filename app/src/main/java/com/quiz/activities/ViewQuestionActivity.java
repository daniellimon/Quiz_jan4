package com.quiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.quiz.R;
import com.quiz.adapter.QuestionsAdapter;
import com.quiz.model.Quiz;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewQuestionActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textMessage)
    TextView textMessage;
    @Bind(R.id.listview_ViewQuestion)
    ListView listviewViewQuestion;
    ArrayList<Quiz> quizList;
    QuestionsAdapter questionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
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

        questionsAdapter = new QuestionsAdapter(baseActivity, quizList);
        listviewViewQuestion.setAdapter(questionsAdapter);

        listviewViewQuestion.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ArrayList<Quiz> quizList1 = new ArrayList<>();
        quizList1.add(quizList.get(i));
        Intent intentStartQuiz = new Intent(baseActivity, StartQuizActivity.class);
        intentStartQuiz.putExtra("Question", quizList1);
        navigation(intentStartQuiz, false);
    }
}
