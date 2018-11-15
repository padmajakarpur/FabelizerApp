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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infinitum.fabelizerapp.Adapter.NavigationDrawerAdapter;
import com.infinitum.fabelizerapp.CustomView.CustomEditText;
import com.infinitum.fabelizerapp.CustomView.CustomTextView;
import com.infinitum.fabelizerapp.DataClass.DataClass;
import com.infinitum.fabelizerapp.Fragments.FragmentHome;
import com.infinitum.fabelizerapp.Fragments.FragmentListYouTube;
import com.infinitum.fabelizerapp.Fragments.FragmentMedia;
import com.infinitum.fabelizerapp.Fragments.FragmentSlider;
import com.infinitum.fabelizerapp.R;
import com.infinitum.fabelizerapp.SessionClass.SessionClass;
import com.infinitum.fabelizerapp.Utils.MyActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityDashBoard extends MyActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private ListView mLstView;
    private ArrayList<DataClass> mArraylist;
    private DrawerLayout mDrawerLayout;
    private ImageView mImgNavIcon,mImgNavIcon1,mImgAdd,mImgSrch,mImgAscDesc,mImgMail,mImgCamera,mImgCross;
    private CustomTextView mTxtViewTitle,mTxtfilter,mTxtName;
    private CustomEditText mEdtSearch;
    private LinearLayout mLinearSrch;
    private CircleImageView mImgprofile;
    private RelativeLayout mReldash;
    private String app_version_name,Version_name;
    private Bitmap mBmp;

    private int[] ic_fabelizer = {
            R.drawable.ic_home,//0
            R.drawable.ic_bench,//1
            R.drawable.ic_media,//2
            R.drawable.ic_referrences,//3
            R.drawable.ic_skin,//4
            R.drawable.ic_lang,//5
            R.drawable.ic_prof,//6
            R.drawable.ic_download,//7
            R.drawable.ic_logout,//8
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
//        if (Build.VERSION.SDK_INT >= 21) {
//                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.green)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
//                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.green)); //status bar or the time bar at the top
//            }


        mLstView=(ListView)findViewById(R.id.lv_drawer);
        mReldash=(RelativeLayout)findViewById(R.id.rel_dash);
        mTxtViewTitle=(CustomTextView) findViewById(R.id.txt_title) ;
        mLstView.setOnItemClickListener(this);
        mArraylist=new ArrayList<>();
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mImgNavIcon=(ImageView)findViewById(R.id.img_menu);
        mImgprofile=(CircleImageView)findViewById(R.id.img_profile_view);
//            mTxtViewTitle=(TextView)findViewById(R.id.txt_prof_name);
        mTxtName=(CustomTextView) findViewById(R.id.txt_name);
        mImgCamera=(ImageView)findViewById(R.id.img_camera_dashboard);
        mImgCamera.setOnClickListener(this);
        mImgNavIcon.setOnClickListener(this);
        mImgprofile.setOnClickListener(this);
        mTxtViewTitle.setText("");
//
//        Intent intent=new Intent(ActivityDashBoard.this, MainActivity.class);
//        startActivity(intent);
        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = FragmentHome.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

//        Fragment fragment = new FragmentHome();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.content_frame, fragment).commit();
        setMenu();

    }
    //  //to set navigation drawer items
    private void setMenu() {
        //Log.e("", "user_type :" + SessionClass.getUserType(MainDashBoardActivity.this));
        String[] arr_menu = null;
        int[] arr_icons = null;
//        if (!SessionClass.getUserId(ActivityDashBoard.this).equalsIgnoreCase("") ) {
            arr_menu = getResources().getStringArray(R.array.str_array_fabelizer);
            arr_icons = ic_fabelizer;
//        }
        mArraylist.clear();
        for (int i = 0; arr_menu != null && i < arr_menu.length; i++) {
            DataClass item = new DataClass();
            item.setMstrNavTitle(arr_menu[i]);
            item.setmStrNavIcon(arr_icons[i]);
            mArraylist.add(item);
        }
        mLstView.setAdapter(new NavigationDrawerAdapter(ActivityDashBoard.this, mArraylist));
    }
    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
            mDrawerLayout.closeDrawers();
        else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {

//                showConfirmationDialog();
            } else {
//                super.onBackPressed();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentHome()).addToBackStack(null).commit();

            }
        }
    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.img_menu:
//                if (!SessionClass.getUserId(ActivityDashBoard.this).equalsIgnoreCase("")) {
                    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//                        mDrawerLayout.requestDisallowInterceptTouchEvent(true);
                        mDrawerLayout.closeDrawers();
                    } else {
//                        mDrawerLayout.requestDisallowInterceptTouchEvent(true);
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    }
//                } else {
//                    Intent intent = new Intent(ActivityDashBoard.this, ActivityLogin.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
//                }
                break;

            case R.id.img_camera_dashboard:
                selectPhoto();
                break;
        }
    }
    //method to select photo
    private void selectPhoto()
    {
        final CharSequence[] items = getResources().getStringArray(R.array.str_arr_image_option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose options");
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
                } else if (item == 2) {
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

        }}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.PNG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".png");
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

            mImgprofile.setImageBitmap(mBmp);


        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        displayFragment(position);
    }

    private void displayFragment(int position) {
        Fragment fragment = null;

        Class fragmentClass = null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                fragment = new FragmentSlider();
//                Intent intent=new Intent(ActivityDashBoard.this, MainActivity.class);
//                startActivity(intent);
                break;
            case 1:
                fragment = new FragmentListYouTube();
                break;
            case 2:
                fragment = new FragmentMedia();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:

                break;
            case 7:
                break;
            case 8:
                //    showDialog(MainDashboardActivity.this,"Do you want to Logout?");
                AlertDialog.Builder aBuilder = new AlertDialog.Builder(ActivityDashBoard.this);
                aBuilder.setCancelable(false);
                aBuilder.setMessage("Do you want to Logout?");
                aBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //stored in session,so get it from session
                        SessionClass.logout(getApplicationContext());
                        Intent intent = new Intent(ActivityDashBoard.this, ActivityLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                        startActivity(intent);
                        finish();
                    }
                });
                aBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
//             AlertDialog alert = aBuilder.create();
                aBuilder.create().show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Functionality under development.", Toast.LENGTH_SHORT).show();
                break;
        }
        if (fragment != null) {
            mDrawerLayout.closeDrawers();
            //mDrawerLayout.closeDrawers(Gravity.left) is also given,but sometimes crashes,so used as follows
//            if (fragment instanceof FragmentMyProfile) {
//                Bundle bundle = new Bundle();
//                fragment.setArguments(bundle);
//            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            ft.replace(R.id.content_frame, fragment).addToBackStack("").commit();
            getFragmentManager().getBackStackEntryCount();
        }
    }
}
