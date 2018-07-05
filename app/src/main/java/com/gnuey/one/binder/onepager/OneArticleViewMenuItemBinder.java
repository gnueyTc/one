package com.gnuey.one.binder.onepager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gnuey.one.R;
import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.ToastUtils;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnueyTc on 2018/6/1.
 */
public class OneArticleViewMenuItemBinder extends ItemViewBinder<OneFlattenBean.MenuBean.ListBean,OneArticleViewMenuItemBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_menu_item,parent,false);
        return new OneArticleViewMenuItemBinder.ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OneFlattenBean.MenuBean.ListBean item) {
        holder.tv_subhead.setText(item.getTitle());
        holder.ly_menu_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showSingleToast("item");
            }
        });
        if(item.getTag()!=null){
            holder.tv_title.setText(item.getTag().getTitle());
            return;
        }
        switch (item.getContent_type()){

            case Constant.TYPE_READ:
                holder.tv_title.setText(R.string.read);
                break;
            case Constant.TYPE_SERIALIZE:
                holder.tv_title.setText(R.string.serialize);
                break;
            case Constant.TYPE_QA:
                holder.tv_title.setText(R.string.qa);
                break;
            case Constant.TYPE_MUSIC:
                holder.tv_title.setText(R.string.music);
                break;
            case Constant.TYPE_MOVIE:
                holder.tv_title.setText(R.string.movie);
                break;

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ly_menu_item;
        private TextView tv_title;
        private TextView tv_subhead;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_subhead = itemView.findViewById(R.id.tv_subhead);
            ly_menu_item = itemView.findViewById(R.id.ly_menu_item);
        }
    }
}
