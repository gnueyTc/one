package com.gnuey.one;

import android.app.Application;

import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerAppComponent;
import com.gnuey.one.module.ApiModule;
import com.gnuey.one.module.AppModule;
import com.gnuey.one.utils.AppUtils;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class InitApp extends Application {
    private static InitApp sIntance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this.getApplicationContext());
        sIntance = this;
        initComponent();
    }

    private void initComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }
    public static InitApp getApplication(){
        return sIntance;
    }
    public AppComponent getAppComponent(){
        return appComponent;
    }
}
