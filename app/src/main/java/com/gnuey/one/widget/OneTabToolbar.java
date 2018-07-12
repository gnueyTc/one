package com.gnuey.one.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gnuey.one.R;
import com.gnuey.one.utils.ExpandAnimationUtil;
import com.gnuey.one.utils.PixelTransformer;

/**
 * Created by gnueyTc on 2018/7/11.
 */
public class OneTabToolbar extends Toolbar {
    private static final String TAG = OneTabToolbar.class.getSimpleName();
    public OneTabToolbar(Context context) {
        super(context);
        initView(context);
    }

    public OneTabToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public OneTabToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View view = View.inflate(context, R.layout.custom_toolbar,this);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"one_title.otf");//自定义字体
        TextView tv_day = view.findViewById(R.id.tv_day);
        tv_day.setTypeface(typeface);
        TextView tv_month_year = view.findViewById(R.id.tv_month_year);
        tv_month_year.setTypeface(typeface);

        ImageView iv_triangle = view.findViewById(R.id.iv_triangle);//三角形指示图标
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_triangle.getLayoutParams();

        ExpandAnimationUtil expandAnimationUtil = ExpandAnimationUtil.getIntance(context,iv_triangle);

        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(() -> {
            //三角形图标设置底部对齐
            lp.setMargins(PixelTransformer.dpToPx(context,5),0,0,view.getHeight()-tv_month_year.getBottom());
            iv_triangle.setLayoutParams(lp);

        });

        Animation animationUp = AnimationUtils.loadAnimation(context,R.anim.anim_arrow_rotation_up);//向上旋转动画
        Animation animationDown = AnimationUtils.loadAnimation(context,R.anim.anim_arrow_rotation_down);//向下旋转动画
        view.setOnClickListener(v -> expandAnimationUtil.rotation(animationUp,animationDown));
    }
}
