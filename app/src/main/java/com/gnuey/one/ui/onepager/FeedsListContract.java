package com.gnuey.one.ui.onepager;


import com.gnuey.one.ui.base.IBasePresenter;
import com.gnuey.one.ui.base.IBaseView;

import java.util.List;


/**
 * Created by gnueyTc on 2018/4/26.
 */
public interface FeedsListContract {
    interface View extends IBaseView{
        /**
         *
         */
        void doSetData(List<?> list);
    }
    interface Presenter extends IBasePresenter{


        void getFeedsList(String date);
    }
}
