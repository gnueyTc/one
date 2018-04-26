package com.gnuey.one.ui.onepager;


import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.ui.base.BaseContract;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public interface IdListContract {
    interface View extends BaseContract.BaseView{
        void showList(IdListBean data);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getIdList(String channel,String version);
    }
}
