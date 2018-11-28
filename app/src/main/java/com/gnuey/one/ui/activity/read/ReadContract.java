package com.gnuey.one.ui.activity.read;

import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.bean.activity.read.ReadActivityBean;
import com.gnuey.one.ui.base.IBasePresenter;
import com.gnuey.one.ui.base.IBaseView;

import java.util.List;

/**
 * Created by gnuey on 2018/11/26/026
 */
public interface ReadContract {
    interface View extends IBaseView {

        /**
         *
         */
        void doSetCommontAdapter(List<?> list);

        /**
         *
         */
        void doSetReadData(ReadActivityBean data);
    }
    interface Presenter extends IBasePresenter {




        void getCommontData(String type,String contentId);

        void getReadData(String type,String contentId);
        /**
         * 设置适配器
         */

        void setCommontAdapter(List<?> list);
    }
}
