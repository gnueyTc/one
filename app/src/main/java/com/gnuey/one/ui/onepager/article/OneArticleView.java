package com.gnuey.one.ui.onepager.article;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.gnuey.one.Register;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.utils.AdapterDiffCallBack;


import java.util.List;

import javax.inject.Inject;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class OneArticleView extends BaseListFragment implements OneArticleContract.View {
    @Inject
    OneArticlePresenter mPresenter;

    public static final String TAG = "OneArticleView";
    private String code;
    public static OneArticleView setArguments(String code) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, code);
        OneArticleView view = new OneArticleView();
        view.setArguments(bundle);
        return view;
    }


    public OneArticleView() {

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
        code = getArguments().getString(OneArticleView.TAG);
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
        mPresenter.doLoadData(Integer.valueOf(code));
        Log.e(TAG, "doOnRefresh: "+code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
