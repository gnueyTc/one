package com.gnuey.one.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by gnuey on 2018/11/17/017
 */
public class CustomRecyclerview extends RecyclerView {
    private Context mContext;
    private LayoutManager layoutManager;
    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    private OnScrollPositionListener onScrollPositionListener;

    public CustomRecyclerview(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public CustomRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    public void initView() {
        this.addOnScrollListener(new ScrollListener());
        this.addItemDecoration(new ItemDecoration());
    }

    public void setScrollPositionListener(OnScrollPositionListener onScrollPositionListener) {
        this.onScrollPositionListener = onScrollPositionListener;
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        this.layoutManager = layout;
    }

    private class ScrollListener extends OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE && onScrollPositionListener != null) {
                View firtstChildView = layoutManager.getChildAt(0);
                View lastChildView = layoutManager.getChildAt(layoutManager.getChildCount() - 1);
                int firstPosition = layoutManager.getPosition(firtstChildView);
                int lastPosition = layoutManager.getPosition(lastChildView);

                if (firstPosition == 0) {
                    onScrollPositionListener.scrollingPosition(TOP);
                    Log.e("CustomRecyclerview", "onScrollStateChanged: top");
                } else if (lastPosition == layoutManager.getItemCount() - 1) {
                    onScrollPositionListener.scrollingPosition(BOTTOM);
                    Log.e("CustomRecyclerview", "onScrollStateChanged: bottom");
                }

            }
        }
    }

    private class ItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if(layoutManager instanceof GridLayoutManager ){
                outRect.bottom = 80;
                if (parent.getChildLayoutPosition(view) % 2 == 0) {
                    outRect.left = 50;
                    outRect.right = 120;
                } else {
                    outRect.left = 120;
                    outRect.right = 50;
                }
            }
        }
    }

    public interface OnScrollPositionListener {
        void scrollingPosition(int position);
    }
}
