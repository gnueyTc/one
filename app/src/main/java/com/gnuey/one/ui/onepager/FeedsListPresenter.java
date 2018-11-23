package com.gnuey.one.ui.onepager;

import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.bean.FeedsListBean;
import com.gnuey.one.ui.base.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class FeedsListPresenter extends RxPresenter<FeedsListContract.View> implements FeedsListContract.Presenter{
    public static final String TAG = FeedsListPresenter.class.getSimpleName();
    private Retrofit retrofit;

    @Inject
    public FeedsListPresenter(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    @Override
    public void getFeedsList(String date) {
        addSubscribe(retrofit.create(OnePagerApi.class).getFeedsList(date)
        .onBackpressureDrop()
        .subscribeOn(Schedulers.io())
                .map(feedsListBean -> {
                    List<FeedsListBean.DataBean> list = feedsListBean.getData();
                    for(int i = 0;i<list.size();i++){
                        if(null==(list.get(i).getCover())||"".equals(list.get(i).getCover())){
                            list.remove(i);
                        }
                    }
                    return list;
                })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dataBeans -> mView.doSetData(dataBeans), throwable -> doShowNetError()));
    }

    @Override
    public void doShowNetError() {
        mView.onShowNetError();
    }
}
