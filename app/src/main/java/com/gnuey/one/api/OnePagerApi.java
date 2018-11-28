package com.gnuey.one.api;


import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.bean.activity.read.CommentBean;
import com.gnuey.one.bean.onepager.FeedsListBean;
import com.gnuey.one.bean.IdListBean;
import com.gnuey.one.bean.activity.read.OneHtmlContentBean;
import com.gnuey.one.bean.onepager.OneListBean;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
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
/**/
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
     * 获取跳转列表
     * http://v3.wufazhuce.com:8000/api/feeds/list/2018-11?user_id=9080706&channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("feeds/list/{date}?user_id=9080706&channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Flowable<FeedsListBean> getFeedsList(
            @Path("date") String date
    );

    /**
     *
     * http://v3.wufazhuce.com:8000/api/essay/htmlcontent/3574?channel=yingyongbao&sign=5db81bdfdd69c84ef79fa962e0d24364&source=menu&source_id=2240&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("{type}/htmlcontent/{code}?channel=yingyongbao&sign=5db81bdfdd69c84ef79fa962e0d24364&source=menu&source_id=2240&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<OneHtmlContentBean> getHtmlContent(
            @Path("type") String type,
            @Path("code") String code
    );
    /**
     *
     * http://v3.wufazhuce.com:8000/api/comment/praiseandtime/essay/3580/0?channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("comment/praiseandtime/{type}/{contentId}/{loadmore}?channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<CommentBean> getComment(
            @Path("type") String type,
            @Path("contentId") String contentId,
            @Path("loadmore") String loadmore
    );

    /**
     *
     * http://v3.wufazhuce.com:8000/api/author/list?content_id=3582&channel=yingyongbao&sign=4bd02b632990e959d7b14c5b2eb07146&category=1&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("author/list?channel=yingyongbao&sign=5b5611118556e39a0cee1eb3f1f87ec2&category=1&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<AuthorBean> getAuthor(
            @Query("content_id") String contenId
    );

}
