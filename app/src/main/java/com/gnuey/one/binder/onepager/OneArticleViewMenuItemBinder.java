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
import com.gnuey.one.utils.EnumType;
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

            case "1":
                holder.tv_title.setText(EnumType.READ.getValue());
                break;
            case "2":
                holder.tv_title.setText(EnumType.SERIALIZE.getValue());
                break;
            case "3":
                holder.tv_title.setText(EnumType.QA.getValue());
                break;
            case "4":
                holder.tv_title.setText(EnumType.MUSIC.getValue());
                break;
            case "5":
                holder.tv_title.setText(EnumType.MOVIE.getValue());
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
