package com.gnuey.one.binder.onepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gnuey.one.R;
import com.gnuey.one.bean.onepage.FeedsListBean;
import com.gnuey.one.utils.DateUtils;
import com.gnuey.one.utils.ImageLoader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/11/9/009
 */
public class OneTabLayoutBinder extends ItemViewBinder<FeedsListBean.DataBean,OneTabLayoutBinder.ViewHolder> {
    public static final String TAG = OneTabLayoutBinder.class.getSimpleName();
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_feeds,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull FeedsListBean.DataBean item) {
        final Context context = holder.itemView.getContext();
        ImageLoader.loadNormal(context,item.getCover(),holder.iv_cover);
        holder.tv_date.setText(item.getDate());
        if(holder.tv_date.getText().equals(DateUtils.getDate())){
            holder.ll_feed.setSelected(true);
        }else {
            holder.ll_feed.setSelected(false);
        }
        holder.ll_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RxBus.getInstance().post(TAG, holder.getLayoutPosition());
                DateUtils.calculaDayApart(item.getDate());
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout ll_feed;
        private ImageView iv_cover;
        private TextView tv_date;
        public ViewHolder(View itemView) {
            super(itemView);
            this.ll_feed = itemView.findViewById(R.id.ll_feed);
            this.iv_cover = itemView.findViewById(R.id.iv_cover);
            this.tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
