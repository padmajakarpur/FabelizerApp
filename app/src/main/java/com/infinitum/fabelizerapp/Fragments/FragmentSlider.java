package com.infinitum.fabelizerapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.infinitum.fabelizerapp.Adapter.AdapterGridView;
import com.infinitum.fabelizerapp.Adapter.AdapterSlidingImages;
import com.infinitum.fabelizerapp.CustomView.CustomTextView;
import com.infinitum.fabelizerapp.R;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class FragmentSlider extends Fragment {
    private View view;
    private ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private GridView mGrid;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.five};
    int logos[] = {R.drawable.skin, R.drawable.lang, R.drawable.game, R.drawable.download,};
    String name[] = {"Skin", "Language", "Game", "Download"};
    private ArrayList<Integer> ImagesArray;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_slider,container,false);
        mPager = (ViewPager)view. findViewById(R.id.pager);
        ImagesArray=new ArrayList<>();
        mGrid=(GridView)view.findViewById(R.id.grid_home);
        ((CustomTextView)getActivity().findViewById(R.id.txt_title)).setText("HOME");
        init();
        return view;
    }

    private void init() {

        mGrid.setAdapter(new AdapterGridView(getActivity(),logos,name));
        for(int i=0;i<IMAGES.length;i++) {
            ImagesArray.add(IMAGES[i]);

        }
        mPager.setAdapter(new AdapterSlidingImages(getActivity(),ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)view.
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

//         Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}
