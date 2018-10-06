package com.infinitum.fabelizerapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by root on 25/7/16.
 */
public class MyActivity extends AppCompatActivity {


    public static SharedPreferences pref;
    public static SharedPreferences pref_addprospects;


    public static double densityFactor=0;
    public static boolean isTutorLogin;
    public static boolean isStudentLogin;

    public static double Latitude;
    public static double Longitude;
    //public static String CITY;
    //public static String CityArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref=getSharedPreferences("details", Context.MODE_PRIVATE);
        pref_addprospects=getSharedPreferences("prospects",Context.MODE_PRIVATE);



//        densityFactor=((double)(getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK))/2;
//        setRequestedOrientation(densityFactor>1? ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR:ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
 public void onSuccess(String response, String tag){}

 public void onFailure(String tag) {}

}
