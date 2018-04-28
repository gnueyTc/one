package com.gnuey.one.ui.base;

/**
 * Created by gnueyTc on 2018/4/28.
 */
public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();

}
