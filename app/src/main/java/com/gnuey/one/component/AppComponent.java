package com.gnuey.one.component;

import android.content.Context;

import com.gnuey.one.module.ApiModule;
import com.gnuey.one.module.AppModule;


import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by gnueyTc on 2017/10/24.
 */
@Singleton
@Component(modules ={AppModule.class, ApiModule.class})
public interface AppComponent {
    Context getContext();
    Retrofit getRetrofit();
}
