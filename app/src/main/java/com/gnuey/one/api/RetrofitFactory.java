package com.gnuey.one.api;

import com.gnuey.one.bean.IdListBean;


import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static RetrofitFactory instance;
    private Retrofit retrofit;

    public RetrofitFactory(OkHttpClient okHttpClient) {
         retrofit = new Retrofit.Builder()
                .baseUrl(OnePagerApi.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public static RetrofitFactory getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new RetrofitFactory(okHttpClient);
        return instance;
    }
    public Observable<IdListBean> getIdList(String channel,String version){
        return retrofit.create(OnePagerApi.class).getIdList(channel,version);
    }
}
