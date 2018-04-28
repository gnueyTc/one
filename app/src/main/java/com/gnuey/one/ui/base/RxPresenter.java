package com.gnuey.one.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxPresenter<T extends IBaseView> implements IBasePresenter{
    protected T mView;

    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
    /**
     * 管理生命周期
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void attachView(T view) {
        this.mView =  view;
    }


    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
