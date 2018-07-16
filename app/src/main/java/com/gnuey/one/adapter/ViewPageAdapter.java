package com.gnuey.one.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by gnueyTc on 2018/7/16.
 */
public class ViewPageAdapter extends PagerAdapter {
    private List<View> mViewList;

    public ViewPageAdapter(List<View> mViewList ){
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        ViewGroup parent = (ViewGroup) container.getParent();
//
//        if (parent != null) {
//
//            parent.removeAllViews();
//
//        }
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }
}
