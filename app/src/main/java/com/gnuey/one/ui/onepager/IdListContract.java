package com.gnuey.one.ui.onepager;


import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.ui.base.IBasePresenter;
import com.gnuey.one.ui.base.IBaseView;


/**
 * Created by gnueyTc on 2018/4/26.
 */
public interface IdListContract {
    interface View extends IBaseView{
        /**
         *
         */
        void doSetData(IdListBean data);
    }
    interface Presenter extends IBasePresenter{


        void getIdList(String channel,String version);
    }
}
