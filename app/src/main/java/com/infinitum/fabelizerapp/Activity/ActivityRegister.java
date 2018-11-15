package com.infinitum.fabelizerapp.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.infinitum.fabelizerapp.CustomView.CustomEditText;
import com.infinitum.fabelizerapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImgPass,mImgPassHide,mImgBack,mImgCameraReg,mImgProf;
    private CustomEditText mEdtpass;
    private Bitmap mBmp;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEdtpass=(CustomEditText)findViewById(R.id.edt_pass_reg);
        mImgBack=(ImageView)findViewById(R.id.img_bck_reg);
        mImgPass=(ImageView)findViewById(R.id.img_pass_reg) ;
        mImgPassHide=(ImageView)findViewById(R.id.img_pass_hide_reg) ;
        mImgProf=(ImageView) findViewById(R.id.img_profile_reg);
        mImgCameraReg=(ImageView)findViewById(R.id.img_camera_reg);
        mImgCameraReg.setOnClickListener(this);

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
            case R.id.img_camera_reg:
                selectPhoto();
                break;
        }
    }
    //method to select camera
    private void selectPhoto()
    {
        final CharSequence[] items = getResources().getStringArray(R.array.str_arr_image_option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    CheckPermission();

                } else if (item == 1) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(intent, 2);
                    dialog.dismiss();

                }
            }
        });
        builder.show();
    }
    //method to check permissions
    private void CheckPermission()
    {
        try
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_REQUEST_CODE);
                }
            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                CheckPermission();

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }
    File file;
    File destination;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.PNG, 90, bytes);
                destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".png");
                FileOutputStream fos;
                try {
                    destination.createNewFile();
                    fos = new FileOutputStream(destination);
                    fos.write(bytes.toByteArray());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mBmp = thumbnail;
            } else {
                if (requestCode == 2) {
                    try {
                        Uri imageuri = data.getData();
                        InputStream is = getContentResolver().openInputStream(imageuri);
                        Bitmap bm = BitmapFactory.decodeStream(is);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG, 90, stream);
                        mBmp = bm;
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            mImgProf.setImageBitmap(mBmp);

        }
    }
}
