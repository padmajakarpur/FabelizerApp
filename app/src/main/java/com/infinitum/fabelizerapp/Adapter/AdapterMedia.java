package com.infinitum.fabelizerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.infinitum.fabelizerapp.CustomView.CustomTextView;
import com.infinitum.fabelizerapp.R;

/**
 * Created by Infinitum on 17/10/2018.
 */

public class AdapterMedia extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private int media[];
    private String name[];

    // Constructor
    public AdapterMedia(Context mContext,int[] media,String name[]){
        this.mContext = mContext;
        this.media = media;
        this.name=name;
        inflater = (LayoutInflater.from(this.mContext));
    }

    @Override
    public int getCount() {
        return media.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        convertView = inflater.inflate(R.layout.fragment_media_list, null); // inflate the layout
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_media);
        CustomTextView mTxtname=(CustomTextView)convertView.findViewById(R.id.txt_nam_media);
        mTxtname.setVisibility(View.VISIBLE);
        mTxtname.setText(name[position]);
        imageView.setImageResource(media[position]);
        return convertView;
    }
}
