package com.gnuey.one.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * Created by gnueyTc on 2018/6/6.
 */
public class CircleTransformation extends BitmapTransformation {
    private static CircleTransformation circleTransformation;
    public static CircleTransformation getIntance(){
        if(circleTransformation==null){
            circleTransformation = new CircleTransformation();
        }
        return circleTransformation;
    }
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool,toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap toTransform) {
        if (toTransform == null) {
            return null;
        }
        int size = Math.min(toTransform.getWidth(), toTransform.getHeight());
        int x = (toTransform.getWidth() - size) / 2;
        int y = (toTransform.getHeight() - size) / 2;

        Bitmap squared = Bitmap.createBitmap(toTransform, x, y, size, size);
        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);

        //绘制边框
        Paint mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(5);//画笔宽度为4px
        mBorderPaint.setColor(Color.WHITE);//边框颜色
        mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        mBorderPaint.setAntiAlias(true);
        float r = size / 2f;
        float r1 = (size - 2 * 4) / 2f;
        canvas.drawCircle(r, r, r1, paint);
        canvas.drawCircle(r, r, r1, mBorderPaint);//画边框
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
