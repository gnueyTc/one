package com.gnuey.one.ui.onepager.article;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.api.RetrofitFactory;
import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.ui.base.RxPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;

public class OneArticlePresenter extends RxPresenter<OneArticleContract.View> implements OneArticleContract.Presenter<OneArticleContract.View> {
    private RetrofitFactory retrofitFactory;
    @Inject
    public OneArticlePresenter(RetrofitFactory retrofitFactory){
        this.retrofitFactory = retrofitFactory;
    }

    @Override
    public void doLoadData(String code,String channel,String version) {
        retrofitFactory.getRetrofitFactory().create(OnePagerApi.class).getOneList(code,channel,version)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OneListBean>() {
                    @Override
                    public void accept(OneListBean oneListBean) throws Exception {
                        OneArticlePresenter.this.doSetAdapter(oneListBean);
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
    public void doSetAdapter(OneListBean dataBeen) {
        Items items = new Items();
        items.add(dataBeen);
        mView.onSetAdapter(items);
    }
}
