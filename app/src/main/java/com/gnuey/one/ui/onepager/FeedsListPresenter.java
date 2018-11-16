package com.gnuey.one.ui.onepager;

import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.bean.FeedsListBean;
import com.gnuey.one.ui.base.RxPresenter;

import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class FeedsListPresenter extends RxPresenter<FeedsListContract.View> implements FeedsListContract.Presenter{
    public static final String TAG = "IdListPresenter";
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
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<FeedsListBean>() {
            @Override
            public void accept(FeedsListBean feedsListBean) throws Exception {
                mView.doSetData(feedsListBean.getData());
            }
        }));
    }

    @Override
    public void doShowNetError() {
        mView.onShowNetError();
    }
}
