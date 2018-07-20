package com.gnuey.one.api;

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
}
