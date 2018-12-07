package com.gnuey.one.binder.activity;

import android.app.Activity;

import android.graphics.Bitmap;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;

import android.webkit.WebResourceRequest;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;


import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import com.gnuey.one.R;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.ui.activity.read.ReadActivity;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.ToastUtils;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityWebViewBinder extends ItemViewBinder<WebBean,ReadActivityWebViewBinder.ViewHolder> {
    private Activity context;
    private final String source = "topic";

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
        holder.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        holder.webView.loadData(item.getUrl(),item.getMimeType(),item.getEncoding());
        holder.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    url = request.getUrl().toString();
                    Log.e("WebViewBinder", "shouldOverrideUrlLoading: "+url);
                }
                if(url.contains("article")){
                        String id = url.substring(url.lastIndexOf("/")).replace("/","");
                        ReadActivity.startReadDetailActivity(context,new String[]{Constant.getType(Constant.TYPE_READ),id,source,item.getId()});
                    }else if(url.contains("question")){
                        String id = url.substring(url.lastIndexOf("/")).replace("/","");
                        ReadActivity.startReadDetailActivity(context,new String[]{Constant.getType(Constant.TYPE_QA),id,source,item.getId()});
                    }else if(url.contains("movie")){
                        String id = url.substring(url.lastIndexOf("/")).replace("/","");
                        ReadActivity.startReadDetailActivity(context,new String[]{Constant.getType(Constant.TYPE_MOVIE),id,source,item.getId()});
                    }else if(url.contains("music")){
                    String id = url.substring(url.lastIndexOf("/")).replace("/","");
                    ReadActivity.startReadDetailActivity(context,new String[]{Constant.getType(Constant.TYPE_MUSIC),id,source,item.getId()});
                }
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                BridgeUtil.webViewLoadLocalJs(view,"WebViewJavascriptBridge.js");

            }
        });
        holder.webView.registerHandler("openRelateDetail", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                function.onCallBack("");
                Log.e("WebViewBinder", "handler: " );
            }
        });
        holder.webView.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("WebViewBinder", "handler: " );
            }
        });

    }
    private boolean isClick;


    @JavascriptInterface
    public void click(int id){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                isClick = isClick?false:true;
                ToastUtils.showSingleToast("click "+id);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private BridgeWebView webView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.webView = itemView.findViewById(R.id.wv_webview);

        }
    }
}
