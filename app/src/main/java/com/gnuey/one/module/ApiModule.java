package com.gnuey.one.module;

import android.util.Log;

import com.gnuey.one.api.RetrofitFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Module
public class ApiModule {
    @Provides
    public OkHttpClient provideOkHttpClien(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
//                .cookieJar(cookieJar)
                .connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
//                        String token = SharedPreferencesUtil.getInstance().getToken("token");
                        Request request = chain.request().newBuilder()
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
