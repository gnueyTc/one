package com.gnuey.one.ui.onepager;

import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.ui.base.RxPresenter;

import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class IdListPresenter extends RxPresenter<IdListContract.View> implements IdListContract.Presenter{
    private static final String TAG = "IdListPresenter";
    private Retrofit retrofit;

    @Inject
    public IdListPresenter(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    @Override
    public void getIdList(String channel, String version) {
        addSubscribe(retrofit.create(OnePagerApi.class)
                .getIdList(channel,version)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(idListBean -> mView.doSetData(idListBean), throwable -> {
                    IdListPresenter.this.doShowNetError();
                    Log.e(TAG, "accept: throwable = " + throwable);
                }));
    }

    @Override
    public void doShowNetError() {
        mView.onShowNetError();
    }
}
