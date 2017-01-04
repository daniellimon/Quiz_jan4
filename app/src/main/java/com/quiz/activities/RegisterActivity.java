package com.quiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.quiz.R;
import com.quiz.utils.AppPreference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textRegisterMessage)
    TextView textRegisterMessage;
    @Bind(R.id.editFullName)
    TextInputEditText editFullName;
    @Bind(R.id.textInputLayoutFullName)
    TextInputLayout textInputLayoutFullName;
    @Bind(R.id.btnSubmit)
    AppCompatButton btnSubmit;
    boolean boolRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolRegister = appPreference.getBoolean(AppPreference.PREF_REGISTER, false);
        if(boolRegister){

        }
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initControls();

    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);


    }

    @OnClick({R.id.btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                boolean boolFirstName = utilityManager.isValidText(editFullName);
                if (boolFirstName) {
                    //write data in shared pref
                    appPreference.setBoolean(AppPreference.PREF_REGISTER, true);
                    appPreference.setString(AppPreference.PREF_USERNAME, editFullName.getText().toString().trim());
                    requstForRegister();
                }
                    if (!boolFirstName) {
                        textInputLayoutFullName.setError(getString(R.string.err_full_name));
                        editFullName.setSelection(editFullName.getText().toString().trim().length());

                }
                break;
        }
    }

    private void requstForRegister() {
        navigation(MainActivity.class, true);
    }
}
