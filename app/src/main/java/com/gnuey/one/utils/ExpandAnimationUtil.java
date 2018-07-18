package com.gnuey.one.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gnuey.one.R;

/**
 * Created by gnueyTc on 2018/6/1.
 */
public class ExpandAnimationUtil {

    private int mHeight;
    private View mSwitcher;
    private View mTagerView;
    private float mDensity;
    private ValueAnimator expandAnimator;
    private ValueAnimator closeAnimator;
    private Animation mAnimationUp;
    private Animation mAnimationDown;
    private boolean click = false;
    public static ExpandAnimationUtil getIntance(Context context, View switcher){
        return new ExpandAnimationUtil(context,switcher);
    }

    /**
     * @param context 上下文
     * @param switcher 指示器

     */
    public ExpandAnimationUtil(Context context, View switcher){
        init(context,switcher);
    }

    private void init(Context context,View switcher){
        this.mSwitcher = switcher;
        this.mAnimationUp = AnimationUtils.loadAnimation(context,R.anim.anim_arrow_rotation_up);
        this.mAnimationDown = AnimationUtils.loadAnimation(context, R.anim.anim_arrow_rotation_down);
        this.mDensity = context.getResources().getDisplayMetrics().density;
    }

    /**
     *
     * @param height 需要展开的高度
     */
    public void setExpanHeight(int height){
        this.mHeight = (int) (mDensity*height+0.5);
    }

    /**
     *
     * @param tagerView 需要展开的view
    */
    public void setTagerView(View tagerView){
        this.mTagerView = tagerView;
    }

    public void taggle(){
        if (mTagerView.getVisibility()== View.VISIBLE){
            switchRotariesAnimation(mAnimationUp);
            closeView(mTagerView);
        }else {
            switchRotariesAnimation(mAnimationDown);
            expandView(mTagerView);
        }
    }

    /**
     *
     */
    public void rotation(){
        switchRotariesAnimation(click?mAnimationUp:mAnimationDown);
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
