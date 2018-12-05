package com.gnuey.one.ui.allpager;


import android.util.Log;

import com.gnuey.one.api.AllPagerApi;
import com.gnuey.one.bean.allpage.AllDataBean;
import com.gnuey.one.bean.allpage.AllListBean;
import com.gnuey.one.ui.base.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by gnuey on 2018/12/1/001
 */
public class AllPresenter extends RxPresenter<AllPageContract.View> implements AllPageContract.Presenter {
    public static final String TAG = AllPresenter.class.getSimpleName();
    public static final String ITEM_BANNER = "3";
    public static final String ITEM_MAIN = "4";
    public static final String ITEM_QA = "5";
    private Retrofit retrofit;
    private List<?> dataList = new ArrayList<>();
    @Inject
    public AllPresenter(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    @Override
    public void doRefresh(String date) {

    }

    @Override
    public void doLoadData(String id) {
        Observable<AllListBean> observableBanner = retrofit.create(AllPagerApi.class).getAllList(ITEM_BANNER,null).subscribeOn(Schedulers.io());
        Observable<AllListBean> observableMain = retrofit.create(AllPagerApi.class).getAllList(ITEM_MAIN,id).subscribeOn(Schedulers.io());
        addSubscribe(Observable.zip(observableBanner, observableMain, new BiFunction<AllListBean, AllListBean, AllDataBean>() {
            @Override
            public AllDataBean apply(AllListBean bannerBean, AllListBean mainBean) throws Exception {
                AllDataBean allDataBean = new AllDataBean();
                if(bannerBean == null){
                    bannerBean = new AllListBean();
                }
                if(mainBean == null){
                  allDataBean.setList(new ArrayList<>());
                }else {
                    allDataBean.setList(mainBean.getData());
                }
                allDataBean.setAllListBean(bannerBean);
                Log.e(TAG, "accept: "+bannerBean.getData().size());
                return allDataBean;
            }
        }).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<AllDataBean>() {
            @Override
            public void accept(AllDataBean allDataBean) throws Exception {
                mView.onSetAdapter(allDataBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: " + throwable );
            }
        }));
//        addSubscribe(retrofit.create(AllPagerApi.class).getAllList(ITEM_MAIN,id)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<AllListBean>() {
//                @Override
//                public void accept(AllListBean allListBean) throws Exception {
//                    mView.onSetAdapter(allListBean.getData());
//                    Log.e(TAG, "accept: " );
//                }
//            }, new Consumer<Throwable>() {
//                @Override
//                public void accept(Throwable throwable) throws Exception {
//                    Log.e(TAG, "accept: " + throwable );
//                }
//            }));
    }

    @Override
    public void doSetAdapter(List<?> list) {

    }

    @Override
    public void doShowNoMore() {

    }

    @Override
    public void doShowNetError() {

    }
}
