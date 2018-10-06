package com.infinitum.fabelizerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.infinitum.fabelizerapp.CustomView.CustomButton;
import com.infinitum.fabelizerapp.CustomView.CustomEditText;
import com.infinitum.fabelizerapp.CustomView.CustomTextView;
import com.infinitum.fabelizerapp.R;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{
    private CustomEditText mEdtEmail,mEdtpass;
    private CustomButton mBtnlogin;
    private CustomTextView mTxtForgot,mTxtReg;
    private ImageView mImgPass,mImgPassHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEdtEmail=(CustomEditText)findViewById(R.id.edt_email_login) ;
        mEdtpass=(CustomEditText)findViewById(R.id.edt_pass_login) ;
        mBtnlogin=(CustomButton)findViewById(R.id.btn_login);
        mTxtReg=(CustomTextView)findViewById(R.id.txt_register);
        mImgPass=(ImageView)findViewById(R.id.img_pass) ;
        mImgPassHide=(ImageView)findViewById(R.id.img_pass_hide) ;
        mTxtForgot=(CustomTextView) findViewById(R.id.txt_forgotPass);

        mImgPass.setVisibility(View.GONE);
        mImgPassHide.setVisibility(View.VISIBLE);
        mEdtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mEdtpass.setInputType(InputType.TYPE_CLASS_TEXT);
        mImgPass.setOnClickListener(this);
        mImgPassHide.setOnClickListener(this);
        mTxtReg.setOnClickListener(this);
        mBtnlogin.setOnClickListener(this);
        mTxtForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.txt_register:
                intent=new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
                break;
            case R.id.txt_forgotPass:
                intent=new Intent(ActivityLogin.this, ActivityForgotPassword.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                intent=new Intent(ActivityLogin.this, ActivityDashBoard.class);
                startActivity(intent);
                break;
            case R.id.img_pass:
                mImgPass.setVisibility(View.GONE);
                mImgPassHide.setVisibility(View.VISIBLE);
                mEdtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mEdtpass.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case R.id.img_pass_hide:
                mImgPass.setVisibility(View.VISIBLE);
                mImgPassHide.setVisibility(View.GONE);
                mEdtpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mEdtpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;

        }
    }
}
