package com.gnuey.one.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by gnueyTc on 2018/6/1.
 */
public class ExpandAnimationUtil {

    private int mHeight;
    private View mTagerView;
    private View mSwitcher;
    private float mDensity;
    private ValueAnimator expandAnimator;
    private ValueAnimator closeAnimator;
    public static ExpandAnimationUtil getIntance(Context context,View tagerView,View switcher){
        return new ExpandAnimationUtil(context,tagerView,switcher);
    }
    /**
     * @param context 上下文
     * @param tagerView 目标控件
     * @param switcher 开关控件
     */
    public ExpandAnimationUtil(Context context,View tagerView,View switcher){
        this.mTagerView = tagerView;
        this.mSwitcher = switcher;
        mDensity = context.getResources().getDisplayMetrics().density;

    }
    /**
     *
     * @param up 向上旋转动画
     * @param down 向下旋转动画
     * @param height 需要展开的高度
     */
    public void taggle(Animation up,Animation down,int height){
        mHeight = (int) (mDensity*height+0.5);
        if (mTagerView.getVisibility()== View.VISIBLE){
            switchRotariesAnimation(up);
            closeView(mTagerView);
        }else {
            switchRotariesAnimation(down);
            expandView(mTagerView);
        }
    }
    /**
     * 开始动画
     * @param animation 需要操作的动画
    */
    private void switchRotariesAnimation(Animation animation){
        mSwitcher.startAnimation(animation);
    }
    private void expandView(View view){
        view.setVisibility(View.VISIBLE);
        if(expandAnimator==null){
            expandAnimator = creatAnimation(view,0,mHeight);//mHeight 需要展开的高度
        }
        expandAnimator.start();
    }
    private void closeView(View view){
        if(closeAnimator==null){
            closeAnimator = creatAnimation(view,mHeight,0);
            closeAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                }
            });
        }
        closeAnimator.start();
    }
    private ValueAnimator creatAnimation(View view,int start,int end){
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.addUpdateListener(animation -> {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = (int) animation.getAnimatedValue();
            view.setLayoutParams(layoutParams);
        });
        animator.setDuration(150);
        return animator;
    }
}
