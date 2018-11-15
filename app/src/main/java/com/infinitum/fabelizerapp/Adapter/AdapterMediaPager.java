package com.infinitum.fabelizerapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.infinitum.fabelizerapp.Fragments.FragmentFeatured;
import com.infinitum.fabelizerapp.Fragments.FragmentFree;
import com.infinitum.fabelizerapp.Fragments.FragmentMedia;
import com.infinitum.fabelizerapp.Fragments.FragmentNew;
import com.infinitum.fabelizerapp.Fragments.FragmentPopular;
import com.infinitum.fabelizerapp.Fragments.FragmentTopRated;

/**
 * Created by Infinitum on 01/11/2018.
 */

public class AdapterMediaPager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    public AdapterMediaPager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
            FragmentFeatured fragmentFeatured=new FragmentFeatured();
            return  fragmentFeatured;
            case 1:
                FragmentNew fragmentNew=new FragmentNew();
                return  fragmentNew;
            case 2:
                FragmentPopular fragmentPopular=new FragmentPopular();
                return  fragmentPopular;
            case 3:
                FragmentTopRated fragmentTopRated=new FragmentTopRated();
                return fragmentTopRated;
            case 4:
                FragmentFree fragmentFree=new FragmentFree();
                return fragmentFree;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }


}
