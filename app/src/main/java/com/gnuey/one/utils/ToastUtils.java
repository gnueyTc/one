package com.gnuey.one.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by gnueyTc on 2017/10/30.
 */

public class ToastUtils {
    private static Toast mToast;
    private static Context mContext = AppUtils.getAppContext();
    public static void showSingleToast(String text){
        getSingleToast(text,Toast.LENGTH_SHORT).show();
    }
    public static void showSingleToast(int resId) { //R.string.**
        getSingleToast(resId, Toast.LENGTH_SHORT).show();
    }
    public static void showSingleLongToast(int resId) {
        getSingleToast(resId, Toast.LENGTH_LONG).show();
    }

    public static Toast getSingleToast(String text,int duration){
        if(mToast==null){
            mToast = Toast.makeText(mContext,text,duration);
        }else {
            mToast.setText(text);
        }
        return mToast;
    }
    public static Toast getSingleToast(int resId,int duration){
       return getSingleToast(mContext.getResources().getText(resId).toString(),duration);
    }
}
