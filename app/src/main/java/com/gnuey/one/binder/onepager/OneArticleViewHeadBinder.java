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
public class OneArticleViewHeadBinder extends ItemViewBinder<OneListBean.DataBean.ContentListBean,OneArticleViewHeadBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_one_head,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OneListBean.DataBean.ContentListBean item) {
        final Context context = holder.itemView.getContext();
        Glide.with(context).load(item.getImg_url());
        holder.tv_title.setText(item.getTitle());
        holder.tv_pic_info.setText(item.getPic_info());
        holder.tv_forward.setText(item.getForward());
        holder.tv_words_info.setText(item.getWords_info());
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView iv_image;
            private TextView tv_title;
            private TextView tv_pic_info;
            private TextView tv_forward;
            private TextView tv_words_info;
        public ViewHolder(View itemView) {
                super(itemView);
                this.iv_image = itemView.findViewById(R.id.iv_image);
                this.tv_title = itemView.findViewById(R.id.tv_title);
                this.tv_pic_info = itemView.findViewById(R.id.tv_pic_info);
                this.tv_forward = itemView.findViewById(R.id.tv_forward);
                this.tv_words_info = itemView.findViewById(R.id.tv_words_info);

            }
        }

}
