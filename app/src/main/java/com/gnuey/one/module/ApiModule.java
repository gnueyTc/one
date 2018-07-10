package com.gnuey.one.module;



import com.gnuey.one.InitApp;
import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.NetWorkUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClien(){
        // 指定缓存路径,缓存大小 50Mb
        Cache cache = new Cache(new File(Constant.PATH_CACHE, "HttpCache"),
                Constant.CACHE_SIZE);
        CacheControlInterceptor cacheControlInterceptor = new CacheControlInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10 , TimeUnit.SECONDS)
                .readTimeout(20 , TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(cacheControlInterceptor)
                .addNetworkInterceptor(cacheControlInterceptor)
                .cache(cache)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit RetrofitFactory(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OnePagerApi.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
