package com.gnuey.one;

import android.app.Application;

import com.gnuey.one.component.AppComponent;

/**
 * Created by gnueyTc on 2018/4/26.
 */
public class InitApp extends Application {
    private static InitApp sIntance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sIntance = this;
    }

    private void initComponent(){

    }
    public static InitApp getApplication(){
        return sIntance;
    }
    public AppComponent getAppComponent(){
        return appComponent;
    }
}
