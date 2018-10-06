package com.infinitum.fabelizerapp.SessionClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

/**
 * Created by andriod_pc on 19/1/16.
 */
public class SessionClass {

    private static SharedPreferences mSharedPreferance;

    private static String FABELIZERAPP = "FabelizerApp";
    private static String USER_ID = "user_id";
    private static String USER_PASS="password";
    private static String USER_IMAGE = "profile_image";
    private static  String NAME="name";



    private static SharedPreferences.Editor mShEditor;

    private static void init(Context mContext) {
        mSharedPreferance = mContext.getSharedPreferences(FABELIZERAPP, Context.MODE_PRIVATE);
//        mSharedPreferance=mContext.getSharedPreferences(USER_Pass,Context.MODE_PRIVATE);
    }

    public static void login(Context mContext,
                             String mUserId,
                             String mUserName,
                             String mUserImage
    )

    {
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mShEditor = mSharedPreferance.edit();
        mShEditor.putString(USER_ID, mUserId);
        mShEditor.putString(NAME, mUserName);
        mShEditor.putString(USER_IMAGE, mUserImage);

        mShEditor.commit();
    }
    public static void logout(Context mContext) {
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mShEditor = mSharedPreferance.edit();
        mShEditor.clear();
        mShEditor.commit();

    }

    public static String getUserId(Context mContext) {
        String mUserId = "";
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mUserId = mSharedPreferance.getString(USER_ID, "");
        return mUserId;
    }
    public static String getNAME(Context mContext) {
        String mUserName = "";
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mUserName = mSharedPreferance.getString(NAME, "");
        return mUserName;
    }
    public static String getPass(Context mContext) {
        String password = "";
        if (mSharedPreferance == null) {
            init(mContext);
        }
        password = mSharedPreferance.getString(USER_PASS, "");
        return password;
    }

    public static String getUserImage(Context mContext) {
        String userImag = "";
        if (mSharedPreferance == null) {
            init(mContext);
        }
        userImag = mSharedPreferance.getString(USER_IMAGE, "");
        return userImag;
    }

    public static void udpdaetPrfileImage(Context mContext, String mImgProfile) {
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mShEditor = mSharedPreferance.edit();
        mShEditor.putString(USER_IMAGE, mImgProfile);
        mShEditor.commit();
    }

    public static void setUserId(Context mContext, String mUserId)
    {
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mShEditor = mSharedPreferance.edit();
        mShEditor.putString(USER_ID, mUserId);
        mShEditor.commit();
    }
    public static void setNAME(Context mContext, String mUserName)
    {
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mShEditor = mSharedPreferance.edit();
        mShEditor.putString(NAME, mUserName);
        mShEditor.commit();
    }

    public static void setPass(Context mContext, String mPassword)
    {
        if (mSharedPreferance == null) {
            init(mContext);
        }
        mShEditor = mSharedPreferance.edit();
        mShEditor.putString(USER_PASS, mPassword);
        mShEditor.commit();
    }

}
