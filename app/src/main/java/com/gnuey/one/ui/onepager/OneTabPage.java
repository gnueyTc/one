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
import com.gnuey.one.utils.RxBus;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class OneTabPage extends Fragment implements IdListContract.View, ViewPager.OnPageChangeListener {
    public static final String TAG = "OneTab";

    @Inject
    IdListPresenter mPresenter;

    private static OneTabPage instance = null;
    private BasePagerAdapter adapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<String> idList;
    private int viewPageSelectedPosition = 0;
    private boolean isPosted = true;
    private int mViewPagerIndex = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(attachLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
        mPresenter.attachView(this);

    }


    protected void initData() throws NullPointerException {
        initChildView();
        adapter = new BasePagerAdapter(getFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(15);


        mPresenter.getIdList("wdj", "4.0.2");

    }

    private OneArticleView oneArticleView;

    private void initChildView() {
        fragmentList = new ArrayList<>();
        oneArticleView = new OneArticleView();
        fragmentList.add(oneArticleView);
    }

    @Override
    public void doSetData(IdListBean data) {
        if (data == null) {
            return;
        }
        idList = data.getData();
        RxBus.getInstance().post(num);
        Log.e(TAG, "showList: " + data.getData().size());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dettachview();
    }

    private boolean isLeft = false;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.e(TAG, "onPageScrolled: position = "+position );
        if (mViewPagerIndex == position) {
            isLeft = true;
            Log.d(TAG, "正在向左滑动");
        } else {
            isLeft = false;
            Log.d(TAG, "正在向右滑动");
        }
    }

    @Override
    public void onPageSelected(int position) {
        viewPageSelectedPosition = position;
//        Log.e(TAG, "onPageSelected: position = " + position);
    }

    private int num = 0;
    @Override
    public void onPageScrollStateChanged(int state) {
        if (idList == null || idList.size() == 0) {
            return;
        }
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            mViewPagerIndex = viewPager.getCurrentItem();
            if (!isLeft) {
//                Log.e(TAG, "onPageScrollStateChanged: IS_ R");
                fragmentList.add(new OneArticleView());
                adapter.recreateItems(fragmentList);
            }
//            Log.e(TAG, "onPageScrollStateChanged: SelectedPosition = "+viewPageSelectedPosition );
//            Log.e(TAG, "onPageScrollStateChanged: IS_ L");
        } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
            if(isLeft){
                num++;
            }else {
                num--;
            }
            RxBus.getInstance().post(num);
            //RxBus.getInstance().post(idList.get(viewPageSelectedPosition + 1));
        } else if (state == ViewPager.SCROLL_STATE_IDLE) {

        }
//

    }
}
