package com.gnuey.one.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by gnueyTc on 2018/6/6.
 */
@GlideModule
public class ImageLoader extends AppGlideModule {
    public static void displayImage(Context context, String uri, ImageView imageView){
        GlideApp.with(context).asDrawable()
                .load(uri)
                .thumbnail(0.1f)
                .transition(withCrossFade())
                .apply(GlideOptions.circleCropTransform())
                .transform(CircleTransformation.getIntance())
                .into(imageView);
    }
    public static void displayImage(Context context, String uri, ImageView imageView,int defaultIconId){
        GlideApp.with(context).asDrawable()
                .load(uri)
                .thumbnail(0.1f)
                .placeholder(defaultIconId)
                .error(defaultIconId)
                .transition(withCrossFade())
                .into(imageView);
    }
}
