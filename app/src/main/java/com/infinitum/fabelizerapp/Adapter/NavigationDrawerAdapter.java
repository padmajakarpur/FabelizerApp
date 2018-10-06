package com.infinitum.fabelizerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.infinitum.fabelizerapp.DataClass.DataClass;
import com.infinitum.fabelizerapp.R;

import java.util.ArrayList;

/**
 * Created by Infinitum on 13/07/2018.
 */

public class NavigationDrawerAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<DataClass> mArrListNavItem;
    private LayoutInflater mInflater;
//    private AQuery mAquery;

    public NavigationDrawerAdapter(Context mContext, ArrayList<DataClass> mArrListNavItem) {
        this.mContext = mContext;
        this.mArrListNavItem = mArrListNavItem;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mAquery = new AQuery(mContext);
    }

    @Override
    public int getCount() {
        return mArrListNavItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrListNavItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_drawer_item, null);
//            mViewHolder.mImgMenu = (ImageView) convertView.findViewById(R.id.img_nav_item_menu);
            mViewHolder.mTxtMenuName = (TextView) convertView.findViewById(R.id.tv_nav_item_title);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mTxtMenuName.setText(mArrListNavItem.get(position).getMstrNavTitle().trim());
//        mAquery.id(mViewHolder.mImgMenu).image(mArrListNavItem.get(position).getmStrNavIcon());

        return convertView;
    }

    private class ViewHolder {
//        ImageView mImgMenu;
        TextView mTxtMenuName;
    }
}
