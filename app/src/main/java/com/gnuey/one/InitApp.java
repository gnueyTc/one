package com.gnuey.one;

import android.app.Application;

import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerAppComponent;
import com.gnuey.one.module.ApiModule;
import com.gnuey.one.module.AppModule;
import com.gnuey.one.utils.AppUtils;
import com.gnuey.one.utils.DateUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class InitApp extends Application {
    private static InitApp sIntance;
    private AppComponent appComponent;
    private static RefWatcher sRefWatcher;
    private static DateUtils dateUtils;
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this.getApplicationContext());
        dateUtils = new DateUtils();
        sIntance = this;
        initComponent();
        sRefWatcher = LeakCanary.install(this);
    }
    private void initComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }
    public static DateUtils getDateUtils(){
        return dateUtils;
    }
    public static InitApp getApplication(){
        return sIntance;
    }
    public AppComponent getAppComponent(){
        return appComponent;
    }
    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }
}
