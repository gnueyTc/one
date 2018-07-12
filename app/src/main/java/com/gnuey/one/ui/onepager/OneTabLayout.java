package com.gnuey.one.ui.onepager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.gnuey.one.InitApp;
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

import butterknife.BindView;


public class OneTabLayout extends BaseFragment implements IdListContract.View,ViewPager.OnPageChangeListener {
    public static final String TAG = "OneTab";

    @Inject
    IdListPresenter mPresenter;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private BasePagerAdapter adapter;
    private List<Fragment> fragmentList;
    private int viewPageSelectedPosition;
    private List<String> idList;
    private OneArticleView oneArticleView;


    @Override
    protected int attachLayoutId() {
        return R.layout.item_one_tab;
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
        setAppComponent(InitApp.getApplication().getAppComponent());
//        initToolBar(toolbar,"");
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(5);
        mPresenter.attachView(this);
    }
    @Override
    protected void initData() throws NullPointerException {
        mPresenter.getIdList("wdj", "4.0.2");
    }

    @Override
    public void doSetData(IdListBean data) {
        if (data == null) {
            return;
        }
        idList = data.getData();
        initChildView(idList);
        adapter = new BasePagerAdapter(getFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        Log.e(TAG, "showList: " + idList.get(viewPageSelectedPosition));
    }

    private void initChildView(List<String> idList) {
        fragmentList = new ArrayList<>();
        for(int i = 0;i<2;i++){
            oneArticleView = OneArticleView.setArguments(idList.get(i));
            fragmentList.add(oneArticleView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewPageSelectedPosition = position;
        if(position == fragmentList.size()-1 && position != idList.size()-1){
            oneArticleView = OneArticleView.setArguments(idList.get(position+1));
            fragmentList.add(oneArticleView);
            adapter.notifyDataSetChanged();
        }
        Log.e(TAG, "onPageSelected: " );
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
