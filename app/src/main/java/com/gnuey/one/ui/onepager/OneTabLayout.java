package com.gnuey.one.ui.onepager;


import android.graphics.Rect;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;


import com.gnuey.one.InitApp;
import com.gnuey.one.R;

import com.gnuey.one.Register;
import com.gnuey.one.adapter.InfiniteFragmentAdapter;

import com.gnuey.one.binder.onepager.OneTabLayoutBinder;
import com.gnuey.one.component.AppComponent;

import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseFragment;
import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.ui.onepager.article.OneArticleView;

import com.gnuey.one.utils.AdapterDiffCallBack;
import com.gnuey.one.utils.DateUtils;
import com.gnuey.one.utils.RxBus;
import com.gnuey.one.widget.OneTabToolbar;


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

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    private final static int SPAN_COUNT = 2;//列表item个数
    private final static int INITIAL_POSITION = -1;//初始position
    private String currentDate = "";//当前页面的时间
    private OneArticleView currentArticleView;//当前显示页面
    private ArrayList<OneArticleView> fragmentList;
    private boolean isShow;
    private int viewPageSelectedPosition;
    private InfiniteFragmentAdapter infiniteFragmentAdapter;
    private int dayApart = 0;
    private MultiTypeAdapter adapter;
    private Items oldItems = new Items();
    private Flowable<Integer> flowableDateUtils;
    private Flowable<Integer> flowableTabLayoutBinder;

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
        initToolBar(toolbar, "");
        final GridLayoutManager layoutManager = new GridLayoutManager(mContext, SPAN_COUNT);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerOneTablayoutItem(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 80;
                if (parent.getChildLayoutPosition(view) % 2 == 0) {
                    outRect.left = 50;
                    outRect.right = 120;
                } else {
                    outRect.left = 120;
                    outRect.right = 50;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);
        toolbar.setDateOnClickListener(() -> getFeedsList());
        mPresenter.attachView(this);
    }


    @Override
    protected void initData() throws NullPointerException {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragmentList.add(new OneArticleView());
        }
        currentArticleView = fragmentList.get(0);//初始化时获取第一个显示页面
        infiniteFragmentAdapter = new InfiniteFragmentAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(infiniteFragmentAdapter);
        flowableDateUtils = RxBus.getInstance().register(DateUtils.TAG, Integer.class);
        flowableDateUtils.subscribe(integer -> {
            dayApart = integer;//当前最新数据与当前日期相差多少
            OneTabLayout.this.setToolbar(dayApart, INITIAL_POSITION);
        });
        flowableTabLayoutBinder = RxBus.getInstance().register(OneTabLayoutBinder.TAG, Integer.class);
        flowableTabLayoutBinder.subscribe(position -> {
            viewPager.setCurrentItem(position);
            isShow = false;
            recyclerView.setVisibility(View.INVISIBLE);
        });
    }

    private boolean isAreadySet;

    private void getFeedsList() {
        isShow = isShow ? false : true;
        recyclerView.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        if (currentDate != null || !"".equals(currentDate) && isShow) {
            if (!isAreadySet) {
                isAreadySet = true;
                mPresenter.getFeedsList(currentDate.substring(0, 7));
            }

        }
    }

    @Override
    public void doSetData(List<?> list) {
        Items newItems = new Items(list);
        AdapterDiffCallBack.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        recyclerView.stopScroll();
    }

    private void setToolbar(int dayApart, int position) {
        Observable.create((ObservableOnSubscribe<String[]>) emitter -> emitter.onNext(InitApp.getDateUtils().getDate(dayApart))).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> {
                    toolbar.setDate(strings[0]);
                    currentDate = strings[1];
                    if (position != INITIAL_POSITION) {
                        OneTabLayout.this.initChildViewData(position, currentDate, 500);
                    }
                });

    }

    private void initChildViewData(int position, String currentDate, long delay) {
        Observable.timer(delay, TimeUnit.MILLISECONDS).subscribe(aLong -> {
            viewPageSelectedPosition = position % fragmentList.size();
            currentArticleView = fragmentList.get(viewPageSelectedPosition);
            currentArticleView.setUnableToLazyLoad(true);
            if (position == 0) {
                currentArticleView.getDate("0").fetchData();
            } else {
                currentArticleView.getDate(currentDate).fetchData();//.doOnRefresh();
            }
        });

    }

    @Override
    public void onDestroy() {
        if (fragmentList != null) {
            fragmentList.clear();
            fragmentList = null;
        }
        RxBus.getInstance().unRegister(BaseListFragment.TAG);
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
        setToolbar(dayApart + position, position);
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
