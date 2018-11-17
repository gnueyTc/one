package com.gnuey.one.ui.onepager.article;


import android.util.Log;

import com.gnuey.one.InitApp;
import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.ui.base.RxPresenter;
import com.gnuey.one.utils.FlattenDataUtils;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class OneArticlePresenter extends RxPresenter<OneArticleContract.View> implements OneArticleContract.Presenter{
    public static final String TAG = OneArticlePresenter.class.getSimpleName();
    private Retrofit retrofit;
    private List<OneFlattenBean> dataList = new ArrayList<>();
    @Inject
    public OneArticlePresenter(Retrofit retrofit){
        this.retrofit = retrofit;
        Log.e(TAG, "OneArticlePresenter: create" );
    }

    @Override
    public void doLoadData(String date) {
        addSubscribe(retrofit.create(OnePagerApi.class).getOneList(date)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(oneListBean -> {
                    if (oneListBean.getData().getContent_list() != null && oneListBean.getData().getContent_list().size() > 0) {
                        OneArticlePresenter.this.doSetAdapter(FlattenDataUtils.FlattenOneListBeanList(oneListBean));
                        InitApp.getDateUtils().calculaDayApart(oneListBean.getData().getDate());

                    } else {
                        OneArticlePresenter.this.doShowNoMore();
                    }

                    Log.e(TAG, "accept: oneListBeanNew Res = "+oneListBean.getData().getDate());
                }, throwable -> {
                    mView.onShowNetError();
                    mView.onHideLoading();
                    Log.e(TAG, "accept: oneListBeanNew ERRO ="+throwable);
                }));
    }

    @Override
    public void doLoadMoreData() {
//            doLoadData(code);
    }

    @Override
    public void doShowNoMore() {
        mView.onHideLoading();
        mView.onShowNoMore();
    }

    @Override
    public void doSetAdapter(List<OneFlattenBean> list) {
        if(dataList.size()!=0){
            dataList.clear();
        }
        dataList.addAll(list);
        mView.onSetAdapter(dataList);
        mView.onHideLoading();
    }

    @Override
    public void doRefresh(String date) {
        if(dataList.size()!=0){
            dataList.clear();
        }
        doLoadData(date);
    }

    @Override
    public void doShowNetError() {
        mView.onShowNetError();
        mView.onHideLoading();
    }
}
