package com.gnuey.one.ui.onepager;


import android.support.v4.view.ViewPager;

import android.util.Log;

import android.view.View;


import com.gnuey.one.InitApp;
import com.gnuey.one.R;

import com.gnuey.one.adapter.InfiniteFragmentAdapter;

import com.gnuey.one.component.AppComponent;

import com.gnuey.one.ui.base.BaseFragment;
import com.gnuey.one.ui.onepager.article.OneArticleView;

import com.gnuey.one.utils.RxBus;
import com.gnuey.one.widget.OneTabToolbar;


import java.util.ArrayList;



import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;


public class OneTabLayout extends BaseFragment implements ViewPager.OnPageChangeListener {
    public static final String TAG = OneTabLayout.class.getSimpleName();



    @BindView(R.id.toolbar)
    OneTabToolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager viewPager;


    private ArrayList<OneArticleView> fragmentList;
    private int viewPageSelectedPosition;
    private InfiniteFragmentAdapter infiniteFragmentAdapter;
    private int dayApart = 0;
    @Override
    protected int attachLayoutId() {
        return R.layout.item_one_tab;
    }

    @Override
    protected void setAppComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView(View view) {
        initToolBar(toolbar, "");
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(0);

    }


    @Override
    protected void initData() throws NullPointerException {
        fragmentList = new ArrayList<>();
        for(int i = 0;i<4;i++){
            fragmentList.add(new OneArticleView());
        }
        infiniteFragmentAdapter = new InfiniteFragmentAdapter(getFragmentManager(), fragmentList);
        viewPager.setAdapter(infiniteFragmentAdapter);
        Flowable<Integer> flowable = RxBus.getInstance().register(Integer.class);
        flowable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                    dayApart = integer;
                    setToolbar(dayApart);
                Log.e(TAG, "accept: dayApart = "+ dayApart );
            }
        });
    }

    private void setToolbar(int dayApart){
        toolbar.setDate(InitApp.getDateUtils().getDate(dayApart));
    }

    @Override
    public void onDestroy() {
        if (fragmentList != null) {
            fragmentList.clear();
            fragmentList = null;
        }

        super.onDestroy();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setToolbar(dayApart+position);
        viewPageSelectedPosition = position % fragmentList.size();
        OneArticleView oneArticleView = fragmentList.get(viewPageSelectedPosition);
        if(position == 0){
            oneArticleView.getDate("0");
        }else {
            oneArticleView.getDate(InitApp.getDateUtils().getSearchDate(dayApart+position));
        }
        Log.e(TAG, "onPageSelected: ");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
}
