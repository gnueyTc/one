package com.gnuey.one.binder.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gnuey.one.R;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.widget.CsWebView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityWebViewBinder extends ItemViewBinder<WebBean,ReadActivityWebViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_webview,parent,false);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull  ViewHolder holder, @NonNull WebBean item) {
        holder.webView.loadData(item.getUrl(),item.getMimeType(),item.getEncoding());
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CsWebView webView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.webView = itemView.findViewById(R.id.wv_webview);
        }
    }
}
