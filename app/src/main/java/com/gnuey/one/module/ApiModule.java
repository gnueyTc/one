package com.gnuey.one.module;



import com.gnuey.one.InitApp;
import com.gnuey.one.api.RetrofitFactory;
import com.gnuey.one.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;



import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Module
public class ApiModule {
    private static final Interceptor cacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetworkConnected(InitApp.getApplication())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetworkConnected(InitApp.getApplication())) {
                // 有网络时 设置缓存为默认值
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma") // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时 设置超时为1周
                int maxStale = 60 * 60 * 24 * 7;
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    @Provides
    public OkHttpClient provideOkHttpClien(){
        // 指定缓存路径,缓存大小 50Mb
        Cache cache = new Cache(new File(InitApp.getApplication().getCacheDir(), "HttpCache"),
                1024 * 1024 * 50);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
//                .cookieJar(cookieJar)
                .connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(cacheControlInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
//                        String token = SharedPreferencesUtil.getInstance().getToken("token");
                        Request request = chain.request().newBuilder()
                                .removeHeader("Pragma")
                                .addHeader("Content-Type", "application/json; charset=UTF-8")
//                                .addHeader("Authorization", "Bearer " + token)
//                                .addHeader("Accept-Encoding", "gzip, deflate")
//                                .addHeader("Connection", "keep-alive")
//                                .addHeader("Accept", "*/*")
//                                .addHeader("Cookie", "add cookies here")
                                .build();
//                        Log.e(TAG, "intercept: toke = " + token);
                        return chain.proceed(request);
                    }
                })
                .retryOnConnectionFailure(true);
        return builder.build();
    }
    @Provides
    protected RetrofitFactory provideRetrofitFactory(OkHttpClient okHttpClient){
        return RetrofitFactory.getInstance(okHttpClient);
    }
}
