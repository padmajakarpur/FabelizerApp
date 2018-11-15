package com.infinitum.fabelizerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.infinitum.fabelizerapp.CustomView.CustomTextView;
import com.infinitum.fabelizerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Infinitum on 08/10/2018.
 */

public class AdapterGridView extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    // Keep all Images in array
     int logos[];
    String name[];

    // Constructor
    public AdapterGridView(Context mContext,int[] logos,String name[]){
        this.mContext = mContext;
        this.logos = logos;
        this.name=name;
        inflater = (LayoutInflater.from(this.mContext));
    }

    @Override
    public int getCount() {
        return logos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.fragment_slider_images, null); // inflate the layout
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        CustomTextView mTxtname=(CustomTextView)convertView.findViewById(R.id.txt_nam_home);
        mTxtname.setVisibility(View.VISIBLE);
        mTxtname.setText(name[position]);
        imageView.setImageResource(logos[position]);

        return convertView;
    }
}
