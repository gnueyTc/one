package com.gnuey.one.ui.onepager.article;

import android.annotation.SuppressLint;
import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.ui.base.RxPresenter;
import com.gnuey.one.utils.FlattenDataUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class OneArticlePresenter extends RxPresenter<OneArticleContract.View> implements OneArticleContract.Presenter{
    private static final String TAG = "OneArticlePresenter";
    private Retrofit retrofit;
    private List<OneFlattenBean> dataList = new ArrayList<>();
    @Inject
    public OneArticlePresenter(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData(int code) {
        retrofit.create(OnePagerApi.class).getOneList(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(oneListBean -> {
                    if(oneListBean.getData().getContent_list()!=null&&oneListBean.getData().getContent_list().size()>0){
                        OneArticlePresenter.this.doSetAdapter(FlattenDataUtils.FlattenOneListBeanList(oneListBean));
//                            OneArticlePresenter.this.doSetAdapter(oneListBean.getData().getContent_list());
                    }else {
                        OneArticlePresenter.this.doShowNoMore();
                    }

                }, throwable -> {
                    OneArticlePresenter.this.doShowNetError();
                    Log.e(TAG, "accept: throwable = "+throwable );
                });
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
        dataList.addAll(list);
        mView.onSetAdapter(dataList);
        mView.onHideLoading();
    }

    @Override
    public void doRefresh(int code) {
        if(dataList.size()!=0){
            dataList.clear();
        }
        mView.onShowLoading();
        doLoadData(code);
    }

    @Override
    public void doShowNetError() {
        mView.onShowNetError();
        mView.onHideLoading();
    }
}
