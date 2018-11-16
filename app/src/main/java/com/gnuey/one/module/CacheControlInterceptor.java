package com.gnuey.one.module;

import android.util.Log;

import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.ui.onepager.OneTabLayout;
import com.gnuey.one.utils.AppUtils;
import com.gnuey.one.utils.NetWorkUtil;
import com.gnuey.one.utils.RxBus;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
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
    private boolean isRefresh = true;//是否刷新
    private final static int CODE = 504;
    public CacheControlInterceptor(){
        Flowable<Boolean> Flowable = RxBus.getInstance().register(BaseListFragment.TAG,Boolean.class);
        Flowable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                isRefresh = aBoolean;
            }
        });
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build();
        Response response = chain.proceed(request);
        Log.e("CacheControlInterceptor", "intercept: originalResponse " + response.code()+ " "+isRefresh);
        if (response.code() == CODE || (isRefresh && NetWorkUtil.isNetworkConnected(AppUtils.getAppContext()))) {
            Response originalResponse = chain.proceed(chain.request());
            int maxAge = 10;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Content-Encoding", "gzip")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }
        // 设置缓存超时时间为一周
        int maxStale = 60 * 60 * 24 * 7;
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .build();
    }
}
