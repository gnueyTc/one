package com.gnuey.one.binder.onepager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gnuey.one.R;
import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.utils.DateUtils;
import com.gnuey.one.utils.EnumType;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnueyTc on 2018/5/2.
 */
public class OneArticleViewBinder extends ItemViewBinder<OneFlattenBean,OneArticleViewBinder.ViewHolder>{
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_one,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OneFlattenBean item) {
        final Context context = holder.itemView.getContext();
        String title = item.getTag_list().size()==0?"":item.getTag_list().get(0).getTitle();
        if(!title.equals("")){
            holder.tv_mainTitle.setText("-"+title+"-");
        }else {
            switch (item.getContent_type()){

                case "1":
                    holder.tv_mainTitle.setText("-"+EnumType.READ.getValue()+"-");
                    break;
                case "2":
                    holder.tv_mainTitle.setText("-"+EnumType.SERIALIZE.getValue()+"-");
                    break;
                case "3":
                    holder.tv_mainTitle.setText("-"+EnumType.QA.getValue()+"-");
                    break;
                case "4":
                    holder.tv_mainTitle.setText("-"+EnumType.MUSIC.getValue()+"-");
                    break;
                case "5":
                    holder.tv_mainTitle.setText("-"+EnumType.MOVIE.getValue()+"-");
                    holder.tv_subtitle.setVisibility(View.VISIBLE);
                    holder.tv_subtitle.setText("——《"+item.getSubtitle()+"》");
                    holder.layout_move.setBackgroundResource(R.drawable.feeds_movie);
                    holder.iv_image.setLayoutParams(holder.layoutParams);
                    break;

            }

        }

        holder.tv_title.setText(item.getTitle());
        holder.tv_author.setText(item.getAuthor().getUser_name());
        Glide.with(context).load(item.getImg_url()).into( holder.iv_image);
        holder.tv_forward.setText(item.getForward());
        holder.tv_date.setText(DateUtils.getTodayDate(item.getPost_date()));

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_mainTitle;
        private TextView tv_title;
        private TextView tv_author;
        private ImageView iv_image;
        private TextView tv_forward;
        private TextView tv_date;
        private TextView tv_subtitle;
        private RelativeLayout layout_move;
        private RelativeLayout.LayoutParams layoutParams;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_mainTitle = itemView.findViewById(R.id.tv_mainTitle);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_author = itemView.findViewById(R.id.tv_authorName);
            this.iv_image = itemView.findViewById(R.id.iv_image);
            this.tv_forward = itemView.findViewById(R.id.tv_forward);
            this.tv_date = itemView.findViewById(R.id.tv_date);
            this.tv_subtitle = itemView.findViewById(R.id.tv_subtitle);
            this.layout_move = itemView.findViewById(R.id.layout_movie);
            this.layoutParams = (RelativeLayout.LayoutParams)iv_image.getLayoutParams();
            this.layoutParams.setMargins(0,50,0,50);
        }
    }
}
