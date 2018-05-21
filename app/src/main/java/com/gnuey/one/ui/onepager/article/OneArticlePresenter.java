package com.gnuey.one.ui.onepager.article;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.api.RetrofitFactory;
import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.ui.base.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;

public class OneArticlePresenter extends RxPresenter<OneArticleContract.View> implements OneArticleContract.Presenter{
    private RetrofitFactory retrofitFactory;
    private List<OneListBean.DataBean.ContentListBean> dataList = new ArrayList<>();
    @Inject
    public OneArticlePresenter(RetrofitFactory retrofitFactory){
        this.retrofitFactory = retrofitFactory;
    }

    @Override
    public void doLoadData(int code) {
        retrofitFactory.getRetrofitFactory().create(OnePagerApi.class).getOneList(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OneListBean>() {
                    @Override
                    public void accept(OneListBean oneListBean) throws Exception {
                        OneArticlePresenter.this.doSetAdapter(oneListBean.getData().getContent_list());
                    }
                });
    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public void doShowNoMore() {

    }

    @Override
    public void doSetAdapter(List<OneListBean.DataBean.ContentListBean> list) {
        dataList.addAll(list);
        mView.onSetAdapter(dataList);
        mView.onHideLoading();
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {

    }
}
