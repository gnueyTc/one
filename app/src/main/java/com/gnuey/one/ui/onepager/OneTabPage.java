package com.gnuey.one.ui.onepager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.gnuey.one.R;
import com.gnuey.one.adapter.BasePagerAdapter;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseFragment;
import com.gnuey.one.ui.onepager.article.OneArticleView;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;




public class OneTabPage extends  BaseFragment implements IdListContract.View,ViewPager.OnPageChangeListener{
    public static final String TAG = "OneTab";

    @Inject
    IdListPresenter mPresenter;

    private static OneTabPage instance = null;
    private BasePagerAdapter adapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;


    @Override
    protected int attachLayoutId() {
        return R.layout.item_viewpager;
    }

    @Override
    protected void setAppComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(this);
        mPresenter.attachView(this);
        mPresenter.getIdList("wdj","4.0.2");
    }

    @Override
    protected void initData() throws NullPointerException {
        initChildView();
        adapter = new BasePagerAdapter(getFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);


    }
    private void initChildView(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new OneArticleView());
    }

    @Override
    public void doSetData(IdListBean data) {
        Log.e(TAG, "showList: "+data.getData().size() );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dettachview();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        adapter.recreateItems(fragmentList);
    }
}
