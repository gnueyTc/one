package com.gnuey.one.ui.onepager;


import com.gnuey.one.bean.IdListBean;


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


        void getIdList(String channel,String version);
    }
}
