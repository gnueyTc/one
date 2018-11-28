package com.gnuey.one.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class CsWebView extends WebView {
    public CsWebView(Context context) {
        super(context);
    }

    public CsWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(0, 0);
    }
}
