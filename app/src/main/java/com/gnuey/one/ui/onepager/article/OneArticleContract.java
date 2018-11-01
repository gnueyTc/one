package com.gnuey.one.ui.onepager.article;

import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.ui.base.IBasePresenter;
import com.gnuey.one.ui.base.IBaseView;

import java.util.List;


public interface OneArticleContract {
    interface View extends IBaseView {

        /**
         * 设置数据
         */
        void onSetAdapter(List<?> list);
    }
    interface Presenter extends IBasePresenter {
        /**
         * 刷新数据
         */
        void doRefresh(String date);

        /**
         * 请求数据
         */
        void doLoadData(String date);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<OneFlattenBean> list);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
