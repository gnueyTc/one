package com.gnuey.one.ui.onepager.article;

import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.ui.base.IBasePresenter;
import com.gnuey.one.ui.base.IBaseView;

import java.util.List;


public interface OneArticleContract {
    interface View extends IBaseView {
        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();

        /**
         * 设置数据
         */
        void onSetAdapter(List<?> list);
    }
    interface Presenter extends IBasePresenter {
        /**
         * 请求数据
         */
        void doLoadData(String code,String channel,String version);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(OneListBean dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
