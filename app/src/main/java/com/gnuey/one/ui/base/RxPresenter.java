package com.gnuey.one.ui.base;

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>{
    protected T mView;
    @Override
    public void attachView(T view) {
        this.mView =  view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
