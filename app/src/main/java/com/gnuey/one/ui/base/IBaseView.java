package com.gnuey.one.ui.base;

import com.uber.autodispose.AutoDisposeConverter;

import java.util.List;

public interface IBaseView {
        /**
         *  显示加载动画
         */
        void onShowLoading();

        /**
         *  隐藏加载
         */
        void onHideLoading();

        /**
         *  显示网络错误
         */
        void onShowNetError();

        /**
         * 加载完毕
         */
        void onShowNoMore();



}
