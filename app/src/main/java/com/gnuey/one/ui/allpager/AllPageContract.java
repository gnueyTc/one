package com.gnuey.one.ui.allpager;

import com.gnuey.one.bean.allpage.AllDataBean;
import com.gnuey.one.bean.onepage.OneFlattenBean;
import com.gnuey.one.ui.base.IBasePresenter;
import com.gnuey.one.ui.base.IBaseView;

import java.util.List;

/**
 * Created by gnuey on 2018/12/1/001
 */
public class AllPageContract {

    interface View extends IBaseView {

        /**
         * 设置数据
         */
        void onSetAdapter(AllDataBean allDataBean);
    }
    interface Presenter extends IBasePresenter {
        /**
         * 刷新数据
         */
        void doRefresh(String date);

        /**
         * 请求数据
         */
        void doLoadData(String id);

        /**
         * 设置适配器
         */
        void doSetAdapter(List<?> list);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
