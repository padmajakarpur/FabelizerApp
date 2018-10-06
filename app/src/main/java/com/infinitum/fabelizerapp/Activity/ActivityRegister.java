package com.infinitum.fabelizerapp.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.infinitum.fabelizerapp.CustomView.CustomEditText;
import com.infinitum.fabelizerapp.R;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImgPass,mImgPassHide,mImgBack;
    private CustomEditText mEdtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEdtpass=(CustomEditText)findViewById(R.id.edt_pass_reg);
        mImgBack=(ImageView)findViewById(R.id.img_bck_reg);
        mImgPass=(ImageView)findViewById(R.id.img_pass_reg) ;
        mImgPassHide=(ImageView)findViewById(R.id.img_pass_hide_reg) ;

        mImgPass.setVisibility(View.GONE);
        mImgPassHide.setVisibility(View.VISIBLE);
        mEdtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mEdtpass.setInputType(InputType.TYPE_CLASS_TEXT);

        mImgPass.setOnClickListener(this);
        mImgPassHide.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.img_pass_reg:
                mImgPass.setVisibility(View.GONE);
                mImgPassHide.setVisibility(View.VISIBLE);
                mEdtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mEdtpass.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case R.id.img_pass_hide_reg:
                mImgPass.setVisibility(View.VISIBLE);
                mImgPassHide.setVisibility(View.GONE);
                mEdtpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mEdtpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.img_bck_reg:
                onBackPressed();
                break;
        }
    }
}
