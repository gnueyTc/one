package com.gnuey.one.ui.onepager.article;


import android.os.Bundle;
import android.os.Handler;
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

    public static final String TAG = OneArticleView.class.getSimpleName();
    private String date = "0";

    public OneArticleView getDate(String date){
//        this.date = date;
//        Log.e(TAG, "initData: date ="+ date );
        Bundle bundle = new Bundle();
        bundle.putString(TAG,date);
        this.setArguments(bundle);
        return this;
    }
    int positon = -1;
    public OneArticleView setPosition(int position){
        positon = position;
        return this;
    }
    public void setAbleToLazyLoad(boolean isAble){
        isAbleToLoad = isAble;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mPresenter.attachView(this);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerOneArticleItem(adapter);
        recyclerView.setAdapter(adapter);
        Log.e(TAG, "initView: ");
    }

    @Override
    protected void initData() {
//        code = Integer.valueOf(getArguments().getString(OneArticleView.TAG));
//        code = getArguments().getInt(OneArticleView.TAG);
    }

    @Override
    public void clearData() {
//        Log.e(TAG, "clearData: " );
//        if(mContext!=null){
//            ImageLoader.clearMemory(GlideApp.get(mContext));
//        }

    }

    @Override
    protected void setAppComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }


    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items(list);
        Log.e(TAG, "onSetAdapter: oldItems = " + oldItems.size() + " newItems = " + newItems.size() );
        AdapterDiffCallBack.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);



    }

    @Override
    public void fetchData() {
        super.fetchData();
        isAbleToLoad = false;
        if(this.getArguments()!=null){
            date = getArguments().getString(TAG);
            mPresenter.doLoadData(date);
        }else {
            mPresenter.doLoadData(date);
        }
        Log.e(TAG, "fetchData: position = "+positon);
    }

    @Override
    public void doOnRefresh() {
        super.doOnRefresh();
        mPresenter.doRefresh(date);
        Log.e(TAG, "doOnRefresh: "+date+" position = "+positon);
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
        Log.e(TAG, "OneArticleView: onDestroy" );
    }
}
