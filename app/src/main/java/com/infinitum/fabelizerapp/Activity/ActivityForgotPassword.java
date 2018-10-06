package com.infinitum.fabelizerapp.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.infinitum.fabelizerapp.R;

public class ActivityForgotPassword extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mImgBack=(ImageView)findViewById(R.id.img_bck_forgot);
        mImgBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.img_bck_forgot:
                onBackPressed();
                break;
        }
    }
}
