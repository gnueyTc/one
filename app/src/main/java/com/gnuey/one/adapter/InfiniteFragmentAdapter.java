package com.gnuey.one.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.gnuey.one.ui.onepager.article.OneArticleView;

import java.util.ArrayList;

/**
 * Created by gnuey on 2018/10/25/025
 */
public class InfiniteFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<OneArticleView> fragmentsList;
    public InfiniteFragmentAdapter(FragmentManager fm,ArrayList<OneArticleView> fragmentsList) {
        super(fm);
        this.fragmentsList = fragmentsList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % fragmentsList.size();
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
