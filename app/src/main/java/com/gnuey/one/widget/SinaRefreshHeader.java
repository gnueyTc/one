package com.gnuey.one.widget;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnuey.one.R;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

public class SinaRefreshHeader extends FrameLayout implements IHeaderView{
    private ImageView loadingView;
    private TextView refreshTextView;
    private String pullDownStr = "下拉刷新...";
    private String releaseRefreshStr = "放开以刷新...";
    private String refreshingStr = "正在载入...";
    private LinearInterpolator lir;
    private Animation anim;
    public SinaRefreshHeader(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SinaRefreshHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public SinaRefreshHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        lir = new LinearInterpolator();
        anim = AnimationUtils.loadAnimation(context,R.anim.anim_rotation);
        anim.setInterpolator(lir);
        View rootView = View.inflate(context, R.layout.item_one_head_loading, null);
        refreshTextView = (TextView) rootView.findViewById(R.id.tv_tiv);
        loadingView = (ImageView) rootView.findViewById(R.id.iv_circle);
        addView(rootView);
    }
    @Override
    public View getView() {
        return this;
    }
    public void setArrowResource(@DrawableRes int resId) {
        loadingView.setImageResource(resId);
    }

    public void setTextColor(@ColorInt int color) {
        refreshTextView.setTextColor(color);
    }
    public void setPullDownStr(String pullDownStr1) {
        pullDownStr = pullDownStr1;
    }

    public void setReleaseRefreshStr(String releaseRefreshStr1) {
        releaseRefreshStr = releaseRefreshStr1;
    }

    public void setRefreshingStr(String refreshingStr1) {
        refreshingStr = refreshingStr1;
    }
    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) refreshTextView.setText(pullDownStr);
        if (fraction > 1f) refreshTextView.setText(releaseRefreshStr);
        loadingView.setRotation(fraction * headHeight / maxHeadHeight * 180);
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (fraction < 1f) {
            refreshTextView.setText(pullDownStr);
            loadingView.setRotation(fraction * headHeight / maxHeadHeight * 180);
//            if (loadingView.getVisibility() == GONE) {
//                loadingView.setVisibility(VISIBLE);
//                loadingView.setVisibility(GONE);
//            }
        }
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        refreshTextView.setText(refreshingStr);
//        ((AnimationDrawable) loadingView.getDrawable()).start();

        loadingView.startAnimation(anim);

    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
        loadingView.clearAnimation();
    }

    @Override
    public void reset() {
        refreshTextView.setText(pullDownStr);
    }
}
