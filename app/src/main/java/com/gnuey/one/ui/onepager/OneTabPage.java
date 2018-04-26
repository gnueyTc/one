package com.gnuey.one.ui.onepager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnuey.one.R;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.ui.onepager.article.OneArticleView;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class OneTabPage extends Fragment implements IdListContract.View{
    public static final String TAG = "OneTab";

    @Inject
    IdListPresenter mPresenter;

    private static OneTabPage instance = null;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter.attachView(this);
        View view = inflater.inflate(R.layout.item_viewpager,container,false);
        viewPager = view.findViewById(R.id.view_pager);
        fragmentList = new ArrayList<>();
        fragmentList.add(new OneArticleView(fragmentList.size()));
        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(fragmentStatePagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                fragmentList.add(new OneArticleView(fragmentList.size()));
                fragmentStatePagerAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void showList(IdListBean data) {
        Log.e(TAG, "showList: "+data.getData().size() );
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    public <K> AutoDisposeConverter<K> bindAutoDispose() {
        return null;
    }
}
