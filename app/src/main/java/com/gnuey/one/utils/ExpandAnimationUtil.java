package com.gnuey.one.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by gnueyTc on 2018/6/1.
 */
public class ExpandAnimationUtil {

    private static ValueAnimator expandAnimator;
    private static ValueAnimator closeAnimator;
    private static boolean click = false;

    /**
     *
     * @param up 向上旋转动画
     * @param down 向下旋转动画
     * @param height 需要展开的高度
     */
    public static void rotation(View icon,Animation up,Animation down,View tagerView,int height){
        if (tagerView.getVisibility()== View.VISIBLE){
            switchRotariesAnimation(icon,up);
            closeView(tagerView,height);
        }else {
            switchRotariesAnimation(icon,down);
            expandView(tagerView,height);
        }
    }

    /**
     *
     * @param up 向上旋转动画
     * @param down 向下旋转动画
    */
    public static void rotation(View icon,Animation up,Animation down){
            switchRotariesAnimation(icon,click?up:down);
            click = click?false:true;
    }

    /**
     * 开始动画
     * @param icon 动画图标
     * @param animation 需要操作的动画
    */
    private static void switchRotariesAnimation(View icon ,Animation animation){
        icon.startAnimation(animation);
    }
    private static void expandView(View view,int height){
        view.setVisibility(View.VISIBLE);
        expandAnimator = creatAnimation(view,0,height);//mHeight 需要展开的高度
        expandAnimator.start();
        expandAnimator.end();
        expandAnimator.cancel();
    }
    private static void closeView(View view,int height){
        closeAnimator = creatAnimation(view,height,0);
        closeAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                }
            });
        closeAnimator.start();
        closeAnimator.end();
        closeAnimator.cancel();
    }
    private static ValueAnimator creatAnimation(View view,int start,int end){
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) animation.getAnimatedValue();
                view.setLayoutParams(layoutParams);
            }
        });
        animator.setDuration(150);
        return animator;
    }
}
