package com.gnuey.one.api;

import com.gnuey.one.bean.FeedsListBean;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.bean.onepager.OneListBean;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OnePagerApi {

    String HOST = "http://v3.wufazhuce.com:8000/api/";

    /**
     * 获取最新 idlist
     * http://v3.wufazhuce.com:8000/api/onelist/idlist/?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android
     *
     * @param
     * @return
    */
    @GET("onelist/idlist/?uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    Observable<IdListBean> getIdList(
            @Query("channel") String channel,
            @Query("version") String version
    );
    /**
     * 获取 onelist
     * http://v3.wufazhuce.com:8000/api/onelist/3528/0?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android
     *
     * @param
     * @return
    */
    @GET("onelist/{code}/0?cchannel=wdj&version=4.1.0" +
            "&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    Flowable<OneListBean> getOneList(
            @Path("code") int code
//            @Query("channel") String channel,
//            @Query("version") String version
    );

    /**
     *
     * 最新api 2018-9-22
     * http://v3.wufazhuce.com:8000/api/channel/one/0/%E6%B8%85%E8%BF%9C?user_id=9080706&channel=14&sign=2d21456947a8ea5f223ff1de333fa3d4&version=4.5.5&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("channel/one/{date}/%E6%B8%85%E8%BF%9C?user_id=9080706&channel=14&sign=2d21456947a8ea5f223ff1de333fa3d4&version=4.5.5&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Flowable<OneListBean> getOneList(
            @Path("date") String date
    );

    /**
     *
     * http://v3.wufazhuce.com:8000/api/feeds/list/2018-11?user_id=9080706&channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("http://v3.wufazhuce.com:8000/api/feeds/list/{date}?user_id=9080706&channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Flowable<FeedsListBean> getFeedsList(
            @Path("date") String date
    );
}
