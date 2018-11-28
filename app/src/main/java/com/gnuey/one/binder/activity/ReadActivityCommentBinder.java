package com.gnuey.one.binder.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gnuey.one.R;
import com.gnuey.one.bean.activity.read.CommentBean;
import com.gnuey.one.utils.ImageLoader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityCommentBinder extends ItemViewBinder<CommentBean.DataBeanX.DataBean,ReadActivityCommentBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull CommentBean.DataBeanX.DataBean item) {
        final Context context = holder.itemView.getContext();
        ImageLoader.displayImage(context,item.getUser().getWeb_url(),holder.iv_comment_head);//加载圆形图片
        holder.tv_user_name.setText(item.getUser().getUser_name());
        holder.tv_upload_date.setText(item.getCreated_at());
        holder.iv_comment_content.setText(item.getContent());
        if(!"".equals(item.getQuote()) && null!= item.getQuote()){
            holder.rl_quote_bg.setVisibility(View.VISIBLE);
            holder.tv_quote.setText(item.getQuote());
        }else {
            holder.rl_quote_bg.setVisibility(View.GONE);
        }
        if(holder.getLayoutPosition() == 9 && 0 == item.getType()){
            holder.tv_hot_comment_tip.setVisibility(View.VISIBLE);
        }else {
            holder.tv_hot_comment_tip.setVisibility(View.GONE);
        }
        Log.e("ReadActivityBinder", "onBindViewHolder: "+item.getType());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_comment_head;
        private TextView tv_user_name;
        private TextView tv_upload_date;
        private TextView iv_comment_content;
        private RelativeLayout rl_quote_bg;
        private TextView tv_quote;
        private TextView tv_hot_comment_tip;
        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_comment_head = itemView.findViewById(R.id.iv_comment_head);
            this.tv_user_name = itemView.findViewById(R.id.tv_user_name);
            this.tv_upload_date = itemView.findViewById(R.id.tv_upload_date);
            this.iv_comment_content = itemView.findViewById(R.id.tv_comment_content);
            this.rl_quote_bg = itemView.findViewById(R.id.rl_quote_bg);
            this.tv_quote = itemView.findViewById(R.id.tv_quote);
            this.tv_hot_comment_tip = itemView.findViewById(R.id.tv_hot_comment_tip);
        }
    }
}
