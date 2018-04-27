package com.gnuey.one.ui.onepager;


import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.ui.base.BaseContract;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public interface IdListContract {
    interface View {
        /**
         *
         */
        void doSetData(IdListBean data);
    }
    interface Presenter{
        /**
         * 绑定view
         */
        void attachView(IdListContract.View view);

        /**
         * 解除绑定view
         */
        void dettachview();

        void getIdList(String channel,String version);
    }
}
