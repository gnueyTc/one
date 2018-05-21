package com.gnuey.one.ui.onepager.article;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.gnuey.one.Register;
import com.gnuey.one.bean.LoadingBean;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.utils.AdapterDiffCallBack;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class OneArticleView extends BaseListFragment implements OneArticleContract.View {
    @Inject
    OneArticlePresenter mPresenter;

    public static final String TAG = "OneArticleView";

    public static OneArticleView setArguments(String code) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, code);
        OneArticleView view = new OneArticleView();
        view.setArguments(bundle);
        return view;
    }


    private int index;
    private Flowable<String> flowable;

    public OneArticleView() {

    }

    @SuppressLint("ValidFragment")
    public OneArticleView(int index) {
        this.index = index;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Log.e(TAG, "initView: ");
        mPresenter.attachView(this);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerOneArticleItem(adapter);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        Log.e(TAG, "initData: ");
//        mPresenter.doLoadData();
    }


    @Override
    protected void setAppComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    public void fetchData() {
        super.fetchData();
        onShowLoading();
        onLoadData();
//        observable = RxBus.getInstance().register(String.class);
//        observable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String string) throws Exception {
//                Log.e(TAG, "accept: num = " + string);
//                mPresenter.doLoadData(Integer.valueOf(string));
//                adapter.notifyDataSetChanged();
////                RxBus.getInstance().unregister(String.class,observable);
//
//            }
//        });
//        Log.e(TAG, "fetchData: " );

//        flowable = RxBus.getInstance().register(String.class);
//        flowable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                if(code.equals("default")){
//                    code = s;
//                    Log.e(TAG, "accept: "+code );
//                }
//
//            }
//        });

    }


    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        AdapterDiffCallBack.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        recyclerView.stopScroll();
    }


    @Override
    public void onLoadData() {
        String code = getArguments().getString(OneArticleView.TAG);
        mPresenter.doLoadData(Integer.valueOf(code));
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onDestroy() {
//        RxBus.getInstance().unregisterAll();
        super.onDestroy();
//        Log.e(TAG, "onDestroy: code = "+num );
    }
}
