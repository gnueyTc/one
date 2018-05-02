package com.gnuey.one.binder.onepager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gnuey.one.R;
import com.gnuey.one.bean.onepager.OneListBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnueyTc on 2018/5/2.
 */
public class OneArticleViewBinder extends ItemViewBinder<OneListBean.DataBean.ContentListBean,OneArticleViewBinder.ViewHolder>{
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_one,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OneListBean.DataBean.ContentListBean item) {
        final Context context = holder.itemView.getContext();
        holder.tv_mainTitle.setText("");
        holder.tv_title.setText(item.getTitle());
        holder.tv_author.setText(item.getAuthor().getUser_name());
        Glide.with(context).load(item.getImg_url()).into( holder.iv_image);
        holder.tv_forward.setText(item.getForward());


    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_mainTitle;
        private TextView tv_title;
        private TextView tv_author;
        private ImageView iv_image;
        private TextView tv_forward;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_mainTitle = itemView.findViewById(R.id.tv_mainTitle);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_author = itemView.findViewById(R.id.tv_authorName);
            this.iv_image = itemView.findViewById(R.id.iv_image);
            this.tv_forward = itemView.findViewById(R.id.tv_forward);
        }
    }
}
