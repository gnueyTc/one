package com.gnuey.one.ui.onepager;

import com.gnuey.one.api.RetrofitFactory;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.ui.base.RxPresenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class IdListPresenter extends RxPresenter<IdListContract.View> implements IdListContract.Presenter<IdListContract.View>{
    private RetrofitFactory retrofitFactory;
    @Inject
    public IdListPresenter(RetrofitFactory retrofitFactory){
        this.retrofitFactory = retrofitFactory;
    }

    @Override
    public void getIdList(String channel, String version) {
        retrofitFactory.getIdList(channel,version)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IdListBean>() {
                    @Override
                    public void accept(IdListBean idListBean) throws Exception {
                        mView.showList(idListBean);
                    }
                });
    }
}
