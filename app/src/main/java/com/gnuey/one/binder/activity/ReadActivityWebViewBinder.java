package com.gnuey.one.binder.activity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.gnuey.one.R;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.ui.activity.read.ReadActivity;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.CsWebView;

import org.w3c.dom.Document;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityWebViewBinder extends ItemViewBinder<WebBean,ReadActivityWebViewBinder.ViewHolder> {
    private Activity context;
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_webview,parent,false);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull  ViewHolder holder, @NonNull WebBean item) {
        context = (ReadActivity)holder.itemView.getContext();
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.loadData(item.getUrl(),item.getMimeType(),item.getEncoding());
        holder.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
//                holder.webView.loadUrl(Constant.JS_INJECT_IMG);
                super.onPageFinished(view, url);
            }
        });
//        holder.webView.addJavascriptInterface(ReadActivityWebViewBinder.this,"listener");
//        webView = holder.webView;

    }
    private boolean isClick;
    private CsWebView webView;
    @JavascriptInterface
    public void click(String span){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                isClick = isClick?false:true;
                ToastUtils.showSingleToast("click "+span);
                webView.loadUrl("javascript:window.togglePlaying()");
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CsWebView webView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.webView = itemView.findViewById(R.id.wv_webview);
        }
    }
}
