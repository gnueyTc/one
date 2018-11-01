package com.gnuey.one.utils;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.gnuey.one.InitApp;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by gnueyTc on 2018/6/6.
 */
@GlideModule
public class ImageLoader extends AppGlideModule {

    public static void displayImage(GlideRequests glideRequests, String uri, ImageView imageView) {

        glideRequests.asDrawable()
                .load(uri)
                .thumbnail(0.1f)
                .transition(withCrossFade())
                .apply(GlideOptions.circleCropTransform())
                .transform(CircleTransformation.getIntance())
                .into(imageView);
    }

    public static void displayImage(GlideRequests glideRequests, String uri, ImageView imageView, int defaultIconId) {

        glideRequests.asDrawable()
                .load(uri)
                .thumbnail(0.1f)
                .placeholder(defaultIconId)
                .error(defaultIconId)
                .transition(withCrossFade())
                .into(imageView);
    }

    public static void loadNormal(GlideRequests glideRequests, String url, ImageView view) {
        glideRequests.load(url).into(view);
    }

    public static void clearMemory(Glide glide) {
        glide.clearMemory();
    }
}
