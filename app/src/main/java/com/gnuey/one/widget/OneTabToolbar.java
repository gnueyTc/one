package com.gnuey.one.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gnuey.one.R;
import com.gnuey.one.ui.onepager.article.OneArticlePresenter;
import com.gnuey.one.utils.ExpandAnimationUtil;
import com.gnuey.one.utils.PixelTransformation;
import com.gnuey.one.utils.RxBus;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by gnueyTc on 2018/7/11.
 */
public class OneTabToolbar extends Toolbar {
    private static final String TAG = OneTabToolbar.class.getSimpleName();
    private View mView;
    private Button mBtToday;
    private TextView mTvweather;
    private TextView mTvDate;
    private ImageView mIvTriangle;
    private Context mContext;
    private DateClickListener dateClickListener;
    private JumpToFirstListener jumpToFirstListener;
    private Animation animationUp;
    private Animation animationDown;
    public int HEIGHT = 0;
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
        this.mContext = context;
        mView = View.inflate(context, R.layout.custom_toolbar,this);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"one_title.otf");//自定义字体
        mTvDate = mView.findViewById(R.id.tv_date);
        mTvDate.setTypeface(typeface);
        mBtToday = mView.findViewById(R.id.bt_today);
        mTvweather = mView.findViewById(R.id.tv_weather);
        mIvTriangle = mView.findViewById(R.id.iv_triangle);//三角形指示图标
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mIvTriangle.getLayoutParams();

        getToolbarHeight(context);
        ViewTreeObserver vto = mView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //三角形图标设置底部对齐
                Log.e(TAG, "initView: v_hight =" + mView.getHeight() + " tv_hight =" + mTvDate.getHeight());
                mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });
        lp.setMargins(PixelTransformation.dpToPx(context,5),0,0,30);
        mIvTriangle.setLayoutParams(lp);
        animationUp = AnimationUtils.loadAnimation(context,R.anim.anim_arrow_rotation_up);//向上旋转动画
        animationDown = AnimationUtils.loadAnimation(context,R.anim.anim_arrow_rotation_down);//向下旋转动画
        mView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAnimation();
                if(dateClickListener != null){
                    dateClickListener.onClick();
                }
            }
        });
        mBtToday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jumpToFirstListener != null){
                    jumpToFirstListener.jump();
                }
            }
        });
        RxBus.getInstance().register(OneArticlePresenter.TAG,String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mTvweather.setText(s);

            }
        });
    }
    public void StartAnimation(){
        ExpandAnimationUtil.rotation(mIvTriangle, animationUp, animationDown);
    }
    public OneTabToolbar setDate(String date){
        SpannableString ss = new SpannableString(date);
        ss.setSpan(new AbsoluteSizeSpan(PixelTransformation.dpToPx(mContext,40)),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new AbsoluteSizeSpan(PixelTransformation.dpToPx(mContext,12)),3,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvDate.setText(ss);
        return this;
    }
    public void weatherShow(int position){
        if(position == 0){
            mBtToday.setVisibility(GONE);
            mTvweather.setVisibility(VISIBLE);
        }else {
            mBtToday.setVisibility(VISIBLE);
            mTvweather.setVisibility(GONE);
        }
    }
    private void getToolbarHeight(Context context){
        TypedValue typedValue = new TypedValue();
        if(context.getTheme().resolveAttribute(android.R.attr.actionBarSize,typedValue,true)){
            HEIGHT = TypedValue.complexToDimensionPixelSize(typedValue.data,getResources().getDisplayMetrics());

        }
    }
    public void setDateOnClickListener(DateClickListener listener){
        this.dateClickListener = listener;
    }
    public void setJumpToFirstListener(JumpToFirstListener listener){
        this.jumpToFirstListener = listener;
    }
    public interface DateClickListener{
        void onClick();
    }
    public interface JumpToFirstListener{
        void jump();
    }
}
