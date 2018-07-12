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
    private View mSwitcher;
    private float mDensity;
    private ValueAnimator expandAnimator;
    private ValueAnimator closeAnimator;
    private boolean click = false;
    public static ExpandAnimationUtil getIntance(Context context,View switcher){
        return new ExpandAnimationUtil(context,switcher);
    }
    /**
     * @param context 上下文
     * @param switcher 指示器
     */
    public ExpandAnimationUtil(Context context,View switcher){
        this.mSwitcher = switcher;
        mDensity = context.getResources().getDisplayMetrics().density;

    }
    /**
     *
     * @param up 向上旋转动画
     * @param down 向下旋转动画
     * @param height 需要展开的高度
     */
    public void taggle(Animation up,Animation down,View tagerView,int height){
        mHeight = (int) (mDensity*height+0.5);
        if (tagerView.getVisibility()== View.VISIBLE){
            switchRotariesAnimation(up);
            closeView(tagerView);
        }else {
            switchRotariesAnimation(down);
            expandView(tagerView);
        }
    }

    /**
     *
     * @param up 向上旋转动画
     * @param down 向下旋转动画
    */
    public void rotation(Animation up,Animation down){
            switchRotariesAnimation(click?up:down);
            click = click?false:true;
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
