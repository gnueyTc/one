package com.gnuey.one.ui.onepager.article;


import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.bean.onepage.OneFlattenBean;
import com.gnuey.one.ui.base.RxPresenter;
import com.gnuey.one.utils.DateUtils;
import com.gnuey.one.utils.FlattenDataUtils;
import com.gnuey.one.utils.RxBus;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                        if("0".equals(date)){
                            DateUtils.calculaDayApart(oneListBean.getData().getDate());
                            RxBus.getInstance().post(TAG,new StringBuffer()
                                    .append(oneListBean.getData().getWeather().getCity_name())
                                    .append("·")
                                    .append(oneListBean.getData().getWeather().getClimate())
                                    .append("\u3000")
                                    .append(oneListBean.getData().getWeather().getTemperature())
                                    .append("℃").toString());
                            Log.e(TAG, "accept: oneListBeanNew Res = "+oneListBean.getData().getDate());
                        }
                    } else {
                        OneArticlePresenter.this.doShowNoMore();
                    }


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
