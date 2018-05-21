package com.gnuey.one.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by gnueyTc on 2018/5/15.
 */
public class CustomViewPager extends ViewPager {
    private final static String TAG = "CustomViewPager";
    public static final int SLIDE_LEFT_TO_RIGHT = 0x001;
    public static final int SLIDE_RIGHT_TO_LEFT = 0x002;
    private float lastVelue;
    private boolean isScrolling = false;  //viewpager是否滑动
    private boolean isRightToLeft = false;  //从右向左滑动
    private boolean isLeftToRight = false;  //从左向右滑动
    private PageStateChangeListener mPageStateChangeListener;
    private float downX;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addOnPageChangeListener(mOnPageChangeListener);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                if ((ev.getX() - downX) < 10) {
                    isScrolling = true;
                    isLeftToRight = true;
                    isRightToLeft = false;
                } else if ((ev.getX() - downX) > 10) {
                    isScrolling = true;
                    isLeftToRight = false;
                    isRightToLeft = true;
                } else {
                    isScrolling = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (positionOffset == 0.0) {
                return;
            }

            lastVelue = positionOffset;
            isScrolling = true;

            if (mPageStateChangeListener != null) {
                mPageStateChangeListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mPageStateChangeListener != null) {
                mPageStateChangeListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mPageStateChangeListener == null) {
                Log.e(TAG, "onPageScrollStateChanged: NULL!!!");
                return;
            }
            mPageStateChangeListener.onPageScrollStateChanged(state);
            if (isScrolling) {
//                Log.e(TAG, "onPageScrollStateChanged: SCROLLING!!!" );
//                if (state == ViewPager.SCROLL_STATE_SETTLING) {
                if (isLeftToRight) {
                    Log.e(TAG, "onPageScrollStateChanged: TO RIGHT!!!");
                    mPageStateChangeListener.onSlideDirection(SLIDE_LEFT_TO_RIGHT);
                } else if (isRightToLeft) {
                    Log.e(TAG, "onPageScrollStateChanged: TO LEFT");
                    mPageStateChangeListener.onSlideDirection(SLIDE_RIGHT_TO_LEFT);
                }
//                }
                isScrolling = false;
            }
        }
    };

    /**
     * 滑动状态改变回调
     */
    public interface PageStateChangeListener {
                void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        void onPageSelected(int position);
        void onPageScrollStateChanged(int state);
        void onSlideDirection(int slideState);
    }

    /**
     * 设置监听
     */
    public void setPageChangeListener(PageStateChangeListener pageStateChangeListener) {
        this.mPageStateChangeListener = pageStateChangeListener;
    }

}
