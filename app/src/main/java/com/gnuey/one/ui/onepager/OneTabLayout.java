package com.gnuey.one.ui.onepager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gnuey.one.InitApp;
import com.gnuey.one.R;
import com.gnuey.one.adapter.BasePagerAdapter;
import com.gnuey.one.adapter.ViewPageAdapter;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseFragment;
import com.gnuey.one.ui.onepager.article.OneArticleView;
import com.gnuey.one.utils.DateUtils;
import com.gnuey.one.widget.OneTabToolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class OneTabLayout extends BaseFragment implements IdListContract.View,ViewPager.OnPageChangeListener {
    public static final String TAG = OneTabLayout.class.getSimpleName();

    @Inject
    IdListPresenter mPresenter;

    @BindView(R.id.toolbar)
    OneTabToolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private BasePagerAdapter adapter;
    private List<Fragment> fragmentList;
    private int viewPageSelectedPosition;
    private List<String> idList;


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
        initToolBar(toolbar,"");
        toolbar.setDate(DateUtils.getDate(0));
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(0);
        mPresenter.attachView(this);
    }

    private List<View> viewList;
    private ViewPageAdapter viewPageAdapter;
    private int index = 0;
    @Override
    protected void initData() throws NullPointerException {
//        mPresenter.getIdList("wdj", "4.0.2");
        justTest();
    }

    private void justTest(){
        viewList = new ArrayList<>();
        for(int i = 0;i<2;i++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.test,parent);
            TextView textView = view.findViewById(R.id.iv_test);
            textView.setText("first");
            viewList.add(view);
        }
        viewPageAdapter = new ViewPageAdapter(viewList);
        viewPager.setAdapter(viewPageAdapter);
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
            fragmentList.add( OneArticleView.setArguments(idList.get(i)));
        }
    }

    @Override
    public void onDestroy() {
        if(fragmentList!=null){
            fragmentList=null;
        }
        mPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        toolbar.setDate(DateUtils.getDate(0-position));
        viewPageSelectedPosition = position;
//        if(position == fragmentList.size()-1 && position != idList.size()-1){
//            fragmentList.add(OneArticleView.setArguments(idList.get(position+1)));
//            adapter.notifyDataSetChanged();
//        }
          if(index<10){
              View view = LayoutInflater.from(mContext).inflate(R.layout.test,parent);
              TextView textView = view.findViewById(R.id.iv_test);
              textView.setText("index = "+index);
              viewList.add(view);
              index++;
              viewPageAdapter.notifyDataSetChanged();
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
