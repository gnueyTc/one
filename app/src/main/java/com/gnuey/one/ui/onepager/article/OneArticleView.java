package com.gnuey.one.ui.onepager.article;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.bumptech.glide.Glide;
import com.gnuey.one.Register;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.utils.AdapterDiffCallBack;
import com.gnuey.one.utils.GlideApp;
import com.gnuey.one.utils.ImageLoader;
import com.gnuey.one.utils.RxBus;


import java.util.List;

import javax.inject.Inject;


import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class OneArticleView extends BaseListFragment implements OneArticleContract.View {
    @Inject
    OneArticlePresenter mPresenter;

    public static final String TAG = OneArticleView.class.getSimpleName();
    private int code;
    private String date = "0";
    public static OneArticleView setArguments(int code) {
        Bundle bundle = new Bundle();
        bundle.putInt(TAG, code);
        OneArticleView view = new OneArticleView();
        view.setArguments(bundle);
        return view;
    }


    public OneArticleView() {

    }

    public void getDate(String date){
        this.date = date;
        Log.e(TAG, "initData: code ="+ date );
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
//        code = Integer.valueOf(getArguments().getString(OneArticleView.TAG));
//        code = getArguments().getInt(OneArticleView.TAG);
    }

    @Override
    public void clearData() {
        Log.e(TAG, "clearData: " );
        if(mContext!=null){
            ImageLoader.clearMemory(GlideApp.get(mContext));
        }

    }

    @Override
    protected void setAppComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }


    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items(list);
        AdapterDiffCallBack.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        recyclerView.stopScroll();

    }

    @Override
    public void doOnRefresh() {
        mPresenter.doLoadData(date);
        Log.e(TAG, "doOnRefresh: "+date);
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        RxBus.getInstance().unregisterAll();
        super.onDestroy();
        Log.e(TAG, "OneArticleView: onDestroy" );
    }
}
