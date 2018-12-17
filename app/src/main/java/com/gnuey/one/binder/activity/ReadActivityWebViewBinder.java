package com.gnuey.one.binder.activity;

import android.app.Activity;

import android.content.Context;
import android.graphics.Bitmap;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.gnuey.one.R;
import com.gnuey.one.bean.ToastBean;
import com.gnuey.one.bean.activity.read.TopicBean;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.ui.activity.read.ReadActivity;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.WVJBWebViewClient;
import com.google.gson.Gson;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityWebViewBinder extends ItemViewBinder<WebBean,ReadActivityWebViewBinder.ViewHolder> {
    private static final String TAG = "WebViewBinder";
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
        String ua = holder.webView.getSettings().getUserAgentString();
        holder.webView.getSettings().setUserAgentString(ua+"OneApp/4.5.6");
        holder.webView.loadData(item.getUrl(),item.getMimeType(),null);
        holder.webView.setWebViewClient(new WebClient(context,holder.webView));

    }
    class WebClient extends WVJBWebViewClient {
        public WebClient(Context context,WebView webView) {
            super(webView, new WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "ObjC Received message from JS: " + data );
                    callback.callback("Response for message from ObjC!");
                }
            });
            registerHandler("showToast", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    ToastBean toastBean = new Gson().fromJson(data.toString(),ToastBean.class);
                    ToastUtils.showSingleToast(toastBean.getText());
                    Log.e(TAG, "request: showToast" + data );
                }
            });
            registerHandler("openRelateDetail", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    TopicBean topicBean = new Gson().fromJson(data.toString(),TopicBean.class);
                    ReadActivity.startReadDetailActivity(context,new String[]{
                            Constant.getType(String.valueOf(topicBean.getCategory()))
                            ,topicBean.getId()
                            ,topicBean.getSource()
                            ,topicBean.getSource_id()
                    });
                    Log.e(TAG, "request: " + data );

                }
            });
            registerHandler("playRadio", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: playRadio" + data );

                }
            });
            registerHandler("stopRadio", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: stopRadio" + data );

                }
            });
            registerHandler("playMusic", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: playMusic " + data );

                }
            });
            registerHandler("stopMusic", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: stopMusic" + data );

                }
            });
        }

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private WebView webView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.webView = itemView.findViewById(R.id.wv_webview);

        }
    }
}
