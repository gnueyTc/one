package com.gnuey.one.ui.onepager.article;

import com.gnuey.one.ui.base.BaseContract;

import java.util.List;


public interface OneArticleContract {
    interface View extends BaseContract.BaseView{
        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<?> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
