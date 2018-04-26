package com.gnuey.one.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by gnueyTc on 2017/10/30.
 */

public class AppUtils {
    private static Context mContext;
    public static void init(Context context){
        mContext = context;
    }
    public static Context getAppContext(){
        return mContext;
    }
    public static Resources getResource(){
        return mContext.getResources();
    }
}
