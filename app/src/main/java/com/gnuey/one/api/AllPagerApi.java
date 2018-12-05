package com.gnuey.one.api;

import com.gnuey.one.bean.allpage.AllListBean;
import com.gnuey.one.bean.allpage.HotAuthors;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by gnuey on 2018/12/1/001
 */
public interface AllPagerApi {

    /**
     *
     * 获取All数据
     * http://v3.wufazhuce.com:8000/api/banner/list/4?last_id=0&channel=yingyongbao&sign=04833aeaba2954bb89f12e7299109a57&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("banner/list/{code}?channel=yingyongbao&sign=04833aeaba2954bb89f12e7299109a57&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<AllListBean> getAllList(
            @Path("code") String code,
            @Query("last_id") String lastId
    );

    /**
     *
     * http://v3.wufazhuce.com:8000/api/author/hot?channel=yingyongbao&sign=8e344af96734912021725415f86e8c8a&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android
     */
    @GET("author/hot?channel=yingyongbao&sign=8e344af96734912021725415f86e8c8a&version=4.5.6&uuid=ffffffff-97de-5b11-ffff-ffffe5557a3d&platform=android")
    Observable<HotAuthors> getHotAuthors();

}
