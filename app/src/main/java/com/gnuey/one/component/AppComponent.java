package com.gnuey.one.component;

import android.content.Context;

import com.gnuey.one.api.RetrofitFactory;
import com.gnuey.one.module.ApiModule;
import com.gnuey.one.module.AppModule;

import dagger.Component;

/**
 * Created by gnueyTc on 2017/10/24.
 */
@Component(modules ={AppModule.class, ApiModule.class})
public interface AppComponent {
    Context getContext();
    RetrofitFactory geRetrofitFactory();
}
