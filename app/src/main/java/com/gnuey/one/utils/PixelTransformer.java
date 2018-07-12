package com.gnuey.one.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by gnueyTc on 2018/7/12.
 */
public class PixelTransformer {
    public static int dpToPx(Context context,int dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,context.getResources().getDisplayMetrics());
    }
    public static int spToPx(Context context,int spValue){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spValue,context.getResources().getDisplayMetrics());
    }
}
