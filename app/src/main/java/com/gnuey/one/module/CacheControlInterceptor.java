package com.gnuey.one.module;

import com.gnuey.one.utils.AppUtils;
import com.gnuey.one.utils.NetWorkUtil;

import java.io.IOException;
import okhttp3.CacheControl;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gnuey on 2018/10/30/030
 */
public class CacheControlInterceptor implements Interceptor {

    /**
     * 缓存机制
     * 默认请求从缓存中获取，如果返回code是504则表示没有缓存，重新
     * 发送请求。每次启动App都必须重新获取最新数据，isRefresh设置为true以获取最新数据。
     */
    private boolean isRefresh;//是否刷新
    private final static int CODE = 504;
    public CacheControlInterceptor(){

    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build();
        Response response = chain.proceed(request);
        if(request.url().encodedPathSegments().contains("0")||request.url().encodedPathSegments().contains("feeds")){
            isRefresh = true;
        }else {
            isRefresh = false;
        }
        if (response.code() == CODE || (isRefresh && NetWorkUtil.isNetworkConnected(AppUtils.getAppContext()))) {

            Response originalResponse = chain.proceed(chain.request());
            int maxAge = 10;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Content-Encoding", "gzip")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }else {
            // 设置缓存超时时间为一周
            int maxStale = 60 * 60 * 24 * 7;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }

    }
}
