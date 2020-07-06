package com.gnuey.one.binder.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gnuey.one.R;
import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.utils.ImageLoader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityAuthorBinder extends ItemViewBinder<AuthorBean.DataBean,ReadActivityAuthorBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_author,parent,false);
        return new ReadActivityAuthorBinder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AuthorBean.DataBean item) {
        final Context context = holder.itemView.getContext();
        ImageLoader.displayImage(context,item.getWeb_url(),holder.iv_author_head);//加载圆形图片
        holder.tv_author.setText(item.getUser_name()+item.getWb_name());
        holder.tv_summary.setText(item.getSummary());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_author_head;
        private TextView tv_author;
        private TextView tv_summary;
        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_author_head = itemView.findViewById(R.id.iv_author_head);
            this.tv_author = itemView.findViewById(R.id.tv_author);
            this.tv_summary = itemView.findViewById(R.id.tv_summary);
        }
    }
}
