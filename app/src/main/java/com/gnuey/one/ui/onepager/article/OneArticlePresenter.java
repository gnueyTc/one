package com.gnuey.one.ui.onepager.article;

import android.annotation.SuppressLint;
import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.api.RetrofitFactory;
import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.ui.base.RxPresenter;
import com.gnuey.one.utils.FlattenDataUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;

public class OneArticlePresenter extends RxPresenter<OneArticleContract.View> implements OneArticleContract.Presenter{
    private static final String TAG = "OneArticlePresenter";
    private RetrofitFactory retrofitFactory;
    private List<OneFlattenBean> dataList = new ArrayList<>();
    @Inject
    public OneArticlePresenter(RetrofitFactory retrofitFactory){
        this.retrofitFactory = retrofitFactory;
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData(int code) {
        retrofitFactory.getRetrofitFactory().create(OnePagerApi.class).getOneList(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OneListBean>() {
                    @Override
                    public void accept(OneListBean oneListBean) throws Exception {
                        if(oneListBean.getData().getContent_list()!=null&&oneListBean.getData().getContent_list().size()>0){
                            OneArticlePresenter.this.doSetAdapter(FlattenDataUtils.FlattenOneListBeanList(oneListBean));
//                            OneArticlePresenter.this.doSetAdapter(oneListBean.getData().getContent_list());
                        }else {
                            OneArticlePresenter.this.doShowNoMore();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        OneArticlePresenter.this.doShowNetError();
                        Log.e(TAG, "accept: throwable = "+throwable );
                    }
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
