package com.gnuey.one.module;




import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.utils.Constant;


import java.io.File;
import java.util.concurrent.TimeUnit;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

import okhttp3.OkHttpClient;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10 , TimeUnit.SECONDS)
                .readTimeout(20 , TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new CacheControlInterceptor())
                .addNetworkInterceptor(new NetWorkInterceptor())
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit RetrofitFactory(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
