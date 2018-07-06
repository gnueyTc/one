package com.gnuey.one.ui.onepager;

import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.api.RetrofitFactory;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.ui.base.RxPresenter;

import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class IdListPresenter implements IdListContract.Presenter{
    private static final String TAG = "IdListPresenter";
    private RetrofitFactory retrofitFactory;
    private IdListContract.View mView;
    @Inject
    public IdListPresenter(RetrofitFactory retrofitFactory){
        this.retrofitFactory = retrofitFactory;
    }


    public void attachView(IdListContract.View view) {
        this.mView = view;
    }


    public void dettachview() {
        mView = null;
    }

    @Override
    public void getIdList(String channel, String version) {
        retrofitFactory.getRetrofitFactory().create(OnePagerApi.class)
                .getIdList(channel,version)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(idListBean -> mView.doSetData(idListBean), throwable -> Log.e(TAG, "accept: throwable = "+throwable ));
    }
}
