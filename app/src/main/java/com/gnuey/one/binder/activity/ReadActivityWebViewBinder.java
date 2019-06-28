package com.gnuey.one.binder.activity;



import android.content.Context;


import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.gnuey.one.R;
import com.gnuey.one.bean.ToastBean;
import com.gnuey.one.bean.activity.read.PlayAudioBean;
import com.gnuey.one.bean.activity.read.TopicBean;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.ui.activity.read.ReadActivity;
import com.gnuey.one.ui.base.BaseActivity;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.MusicDbUtils;
import com.gnuey.one.utils.RxBus;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.WVJBWebViewClient;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityWebViewBinder extends ItemViewBinder<WebBean,ReadActivityWebViewBinder.ViewHolder> {
    public static final String TAG = ReadActivityWebViewBinder.class.getSimpleName();
    public static final String STOP_PLAYING = "stop";
    private BaseActivity context;
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
        holder.webView.getSettings().setDomStorageEnabled(true);
        String ua = holder.webView.getSettings().getUserAgentString();
        holder.webView.getSettings().setUserAgentString(ua+"OneApp/4.5.6");
        holder.webView.loadData(parseHtml(item.getUrl()),item.getMimeType(),null);
        WebClient client = new WebClient(context,holder.webView);
        client.enableLogging();
        holder.webView.setWebViewClient(client);
        holder.webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e(TAG, "onConsoleMessage: "+consoleMessage.message() );
                return super.onConsoleMessage(consoleMessage);
            }
        });

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
            registerHandler("getOneStatus", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: getOneStatus" + data );
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
            registerHandler("saveStatData", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {

                    Log.e(TAG, "request: saveStatData" + data );

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
                    Log.e(TAG, "request: openRelateDetail" + data );

                }
            });
            registerHandler("playMovie", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: playMovie" + data );

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
            registerHandler("prepareAudios", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {

                }
            });
            registerHandler("playAudio", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: playAudio" + data );
                }
            });
            registerHandler("stopAudio", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: stopAudio" + data );

                }
            });
            registerHandler("playReadingAudio", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: playReadingAudio" + data );
                    callHandler("setPlayingAudio",new Object());
                    try {

                        callHandler("setPlayingAudio", new JSONObject("{\"isPlaying\":true,\"percentage\":\"10\",\"remainingTime\":20}"), new WVJBResponseCallback() {
                            @Override
                            public void callback(Object data) {
                                Log.e(TAG, "request: playReadingAudio" + data );
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "request: playReadingAudio" + e );
                    }
                }
            });
            registerHandler("setReadingAudio", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: setReadingAudio" + data );
                    callback.callback(data);
                }
            });
            registerHandler("stopReadingAudio", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: stopReadingAudio" + data );

                }
            });
            registerHandler("playMusic", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: playMusic " + data );
                    PlayAudioBean playAudioBean = new Gson().fromJson(data.toString(),PlayAudioBean.class);
                    RxBus.getInstance().post(TAG,playAudioBean.getAudio_url());
                    MusicDbUtils.getInstance().saveAudio(playAudioBean);
                }
            });
            registerHandler("prepareMusic", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: prepareMusic" + data );
                   callHandler("setPlayingMusic");
                }
            });

            registerHandler("stopMusic", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: stopMusic" + data );
                    RxBus.getInstance().post(TAG,STOP_PLAYING);
                }
            });
            registerHandler("toggleNavbar", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: toggleNavbar" + data );

                }
            });
            registerHandler("openUserHome", new WVJBWebViewClient.WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Log.e(TAG, "request: openUserHome" + data );

                }
            });

        }

    }
    private String parseHtml(String HTML){
        Document document = Jsoup.parse(HTML);
        Element authorsElement = document.select("div.one-authors-box").first();
        Element copyrightElement = document.select("div.one-copyright-box").first();
        Element relatesElement = document.select("div.one-relates-box").first();
        Element commentsElement = document.select("div.one-comments-box").first();
//        Element readingElement = document.select("div.onevue-readingaudio-box").first();
        if(authorsElement != null){
            authorsElement.remove();
        }
        if(copyrightElement != null){
            copyrightElement.remove();
        }
        if(relatesElement != null){
            relatesElement.remove();
        }
        if(commentsElement != null){
            commentsElement.remove();
        }
//        if(readingElement != null){
//            readingElement.remove();
//        }
        return document.toString();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private WebView webView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.webView = itemView.findViewById(R.id.wv_webview);

        }
    }
}
