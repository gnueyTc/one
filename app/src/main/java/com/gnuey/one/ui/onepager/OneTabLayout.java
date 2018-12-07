package com.gnuey.one.ui.onepager;


import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.View;


import com.gnuey.one.MainActivity;
import com.gnuey.one.R;

import com.gnuey.one.Register;
import com.gnuey.one.adapter.onepage.InfiniteFragmentAdapter;

import com.gnuey.one.adapter.helper.ViewPageHelper;
import com.gnuey.one.binder.onepage.OneTabLayoutBinder;
import com.gnuey.one.component.AppComponent;

import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseFragment;
import com.gnuey.one.ui.onepager.article.OneArticleView;

import com.gnuey.one.utils.AdapterDiffCallBack;
import com.gnuey.one.utils.DateUtils;
import com.gnuey.one.utils.RxBus;
import com.gnuey.one.widget.OneTabToolbar;
import com.gnuey.one.widget.TimeSelectLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class OneTabLayout extends BaseFragment implements FeedsListContract.View, ViewPager.OnPageChangeListener {
    public static final String TAG = OneTabLayout.class.getSimpleName();

    @Inject
    FeedsListPresenter mPresenter;

    @BindView(R.id.toolbar)
    OneTabToolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.ry_time)
    TimeSelectLayout timeSelectLayout;

    private MainActivity mainActivity;
    private String currentDate = "";//当前页面的时间
    private OneArticleView currentArticleView;//当前显示页面
    private ArrayList<OneArticleView> fragmentList;
    private boolean isShow;//是否显示feedsList
    private boolean isFromFeedsList;//是否来自跳转
    private int viewPageSelectedPosition;
    private InfiniteFragmentAdapter infiniteFragmentAdapter;
    private int dayApart = -1;//日差值
    private MultiTypeAdapter adapter;
    private Items oldItems = new Items();
    private Flowable<Integer> flowableDateUtils;
    private ViewPageHelper viewPageHelper;

    @Override
    protected int attachLayoutId() {
        return R.layout.layout_one;
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
        mainActivity = (MainActivity) getActivity();
        initToolBar(toolbar, "");
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerOneTabLayoutItem(adapter);
        timeSelectLayout.setGetDateListener(new TimeSelectLayout.GetDateListener() {
            @Override
            public void getDate(String date) {
                mPresenter.getFeedsList(date);
            }
        });

        timeSelectLayout.setAdapter(adapter);
//        viewPager.setPageTransformer(true,new DepthPageTransformer());
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);
        viewPageHelper = new ViewPageHelper(viewPager);
        toolbar.setDateOnClickListener(() -> getFeedsList());
        toolbar.setJumpToFirstListener(new OneTabToolbar.JumpToFirstListener() {
            @Override
            public void jump() {
                viewPageHelper.setCurrentItem(0);
            }
        });
        mPresenter.attachView(this);
    }


    @SuppressLint("CheckResult")
    @Override
    protected void initData() throws NullPointerException {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragmentList.add(new OneArticleView().setPosition(i));
        }
        currentArticleView = fragmentList.get(0);//初始化时获取第一个显示页面
        currentArticleView.setAbleToLazyLoad(true);//启用懒加载
        infiniteFragmentAdapter = new InfiniteFragmentAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(infiniteFragmentAdapter);
        flowableDateUtils = RxBus.getInstance().register(DateUtils.TAG, Integer.class);
        flowableDateUtils.subscribe(integer -> {
            OneTabLayout.this.setToolbar(integer);
            if (dayApart == -1) {
                dayApart = integer;//最新数据的日差
                return;
            }
            viewPageHelper.setCurrentItem(integer - dayApart, false);//跳转到选择的页面
            toolbar.StartAnimation();//还原动画
            mainActivity.bottomNavigation.setVisibility(View.VISIBLE);
            isShow = false;
            timeSelectLayout.show(isShow);
            Log.e(TAG, "initData: dayApart" + dayApart);
        });

    }

    private void getFeedsList() {
        String date="";
        isShow = isShow ? false : true;
        timeSelectLayout.show(isShow);
        mainActivity.bottomNavigation.setVisibility(isShow ? View.INVISIBLE : View.VISIBLE);
        if (isShow) {
            date = DateUtils.getDate();
            if(!"".equals(date)||date.length()>=8){
                mPresenter.getFeedsList(date.substring(0, 7));
            }
        }

    }

    @Override
    public void doSetData(List<?> list) {
        Items newItems = new Items(list);
        AdapterDiffCallBack.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
//        recyclerView.stopScroll();
    }

    private void setToolbar(int dayApart) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> emitter.onNext(DateUtils.getDate(dayApart))).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(date -> toolbar.setDate(date));

    }

    @Override
    public void onDestroy() {
        if (fragmentList != null) {
            fragmentList.clear();
            fragmentList = null;
        }
        RxBus.getInstance().unRegister(DateUtils.TAG);
        RxBus.getInstance().unRegister(OneTabLayoutBinder.TAG);
        mPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        toolbar.weatherShow(position);
        viewPageSelectedPosition = position % fragmentList.size();//取模获取当前显示的view的position
        currentArticleView = fragmentList.get(viewPageSelectedPosition);
        setToolbar(position + dayApart);//如果只是滑动而不是来自setCurrentItem的跳转必须加上日差
        Log.e(TAG, "onPageSelected: " + dayApart);
        currentArticleView.getDate(DateUtils.getSearchDate(position + dayApart));
        Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> {
            currentArticleView.fetchData();
        });

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
