package com.gnuey.one.binder.allpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import com.gnuey.one.R;
import com.gnuey.one.bean.allpage.AllListBean;
import com.gnuey.one.ui.activity.read.ReadActivity;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.ImageLoader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/12/2/002
 */
public class AllTabLayoutBinder extends ItemViewBinder<AllListBean.DataBean,AllTabLayoutBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.fragment_all,parent,false);
        return new AllTabLayoutBinder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AllListBean.DataBean item) {
        final Context context = holder.itemView.getContext();
        ImageLoader.loadNormal(context,item.getCover(),holder.iv_cover);
        holder.tv_title.setText(item.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadActivity.startReadDetailActivity(context,new String[]{Constant.getType(String.valueOf(item.getCategory())),item.getContent_id()});
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_cover;
        private TextView tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_cover = itemView.findViewById(R.id.iv_cover);
            this.tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
