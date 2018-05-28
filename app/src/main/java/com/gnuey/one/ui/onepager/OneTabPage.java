package com.gnuey.one.ui.onepager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnuey.one.InitApp;
import com.gnuey.one.R;
import com.gnuey.one.adapter.BasePagerAdapter;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.onepager.article.OneArticleView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class OneTabPage extends Fragment implements IdListContract.View,ViewPager.OnPageChangeListener {
    public static final String TAG = "OneTab";

    @Inject
    IdListPresenter mPresenter;

    private BasePagerAdapter adapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<String> idList;
    private int viewPageSelectedPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(attachLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }


    protected int attachLayoutId() {
        return R.layout.item_viewpager;
    }


    protected void setAppComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }


    protected void initView(View view) {
        setAppComponent(InitApp.getApplication().getAppComponent());
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(5);
        mPresenter.attachView(this);
    }

    protected void initData() throws NullPointerException {
        mPresenter.getIdList("wdj", "4.0.2");
    }

    private OneArticleView oneArticleView;

    private void initChildView() {
        fragmentList = new ArrayList<>();
        for(int i = 0;i<2;i++){
            oneArticleView = OneArticleView.setArguments(idList.get(i));
            fragmentList.add(oneArticleView);
        }

    }

    @Override
    public void doSetData(IdListBean data) {
        if (data == null) {
            return;
        }
        idList = data.getData();
        initChildView();
        adapter = new BasePagerAdapter(getFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        Log.e(TAG, "showList: " + idList.get(viewPageSelectedPosition));
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
        viewPageSelectedPosition = position;
        if(position==fragmentList.size()-1&&position!=idList.size()-1){
            oneArticleView = OneArticleView.setArguments(idList.get(position+1));
            fragmentList.add(oneArticleView);
            adapter.notifyDataSetChanged();
        }
        Log.e(TAG, "onPageSelected: " );
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
