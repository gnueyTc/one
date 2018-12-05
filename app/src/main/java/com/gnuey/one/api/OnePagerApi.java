package com.gnuey.one.api;


import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.bean.activity.read.CommentBean;
import com.gnuey.one.bean.onepage.FeedsListBean;
import com.gnuey.one.bean.activity.read.OneHtmlContentBean;
import com.gnuey.one.bean.onepage.OneListBean;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OnePagerApi {

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
     * 获取阅读内容
     * http://v3.wufazhuce.com:8000/api/essay/htmlcontent/3587?channel=yingyongbao&sign=af01ef4071b23330ee84dbb22c034964&source=summary&source_id=16824&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("{type}/htmlcontent/{code}?channel=yingyongbao&sign=af01ef4071b23330ee84dbb22c034964&source=menu&source_id=2240&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<OneHtmlContentBean> getHtmlContent(
            @Path("type") String type,
            @Path("code") String code
    );
    /**
     * 获取评论内容
     * http://v3.wufazhuce.com:8000/api/comment/praiseandtime/essay/3580/0?channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("comment/praiseandtime/{type}/{contentId}/{loadmore}?channel=yingyongbao&sign=0cf43d3f6dca829f562b9060404cd8b5&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<CommentBean> getComment(
            @Path("type") String type,
            @Path("contentId") String contentId,
            @Path("loadmore") String loadmore
    );

    /**
     * 获取作者信息
     * http://v3.wufazhuce.com:8000/api/author/list?content_id=2846&channel=yingyongbao&sign=a175d093b24ca4caf76878bc7adab2d4&category=4&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("author/list?channel=yingyongbao&sign=bc8b98a644731786ab435d570fae3b4a&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<AuthorBean> getAuthor(
            @Query("category") String category,
            @Query("content_id") String contenId
    );

}
