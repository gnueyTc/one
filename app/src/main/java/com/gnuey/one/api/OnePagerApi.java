package com.gnuey.one.api;

import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.bean.onepager.OneListBean;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface OnePagerApi {

    String HOST = "http://v3.wufazhuce.com:8000/api/";

    /**
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
     * http://v3.wufazhuce.com:8000/api/onelist/3528/0?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android
     *
     * @param
     * @return
    */
    @GET("onelist/{code}/0?uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    Observable<OneListBean> getOneList(
            @Part("code") String code,
            @Query("channel") String channel,
            @Query("version") String version
    );
}
