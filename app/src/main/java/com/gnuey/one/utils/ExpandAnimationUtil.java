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
     * @param up 旋转动画
     * @param down 旋转动画
     * @param height 需要展开的高度
     */
    public void taggle(Animation up,Animation down,int height){
        mHeight = (int) (mDensity*height+0.5);
        if (mTagerView.getVisibility()== View.VISIBLE){
            switchAnimation(up);
            closeView(mTagerView);
        }else {
            switchAnimation(down);
            expandView(mTagerView);
        }
    }
    private void switchAnimation(Animation animation){
        mSwitcher.startAnimation(animation);
    }
    private void expandView(View view){
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = creatAnimation(view,0,mHeight);
        animator.start();
    }
    private void closeView(View view){
        int origHeight = view.getHeight();
        ValueAnimator animator = creatAnimation(view,origHeight,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }
    private ValueAnimator creatAnimation(View view,int start,int end){
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
