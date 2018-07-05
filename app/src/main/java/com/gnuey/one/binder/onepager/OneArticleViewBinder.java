package com.gnuey.one.binder.onepager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gnuey.one.R;
import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.utils.Constant;
import com.gnuey.one.utils.DateUtils;
import com.gnuey.one.utils.EnumType;
import com.gnuey.one.utils.ImageLoader;


import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnueyTc on 2018/5/2.
 */
public class OneArticleViewBinder extends ItemViewBinder<OneFlattenBean,OneArticleViewBinder.ViewHolder>{
    private static final String TAG = "OneArticleViewBinder";
    private boolean isPlay = true;//是否点击播放
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
                case Constant.TYPE_READ:
                    holder.tv_mainTitle.setText(R.string.read_);
                    break;
                case Constant.TYPE_SERIALIZE:
                    holder.tv_mainTitle.setText(R.string.serialize_);
                    break;
                case Constant.TYPE_QA:
                    holder.tv_mainTitle.setText(R.string.qa_);
                    break;
                case Constant.TYPE_MUSIC:
                    holder.tv_mainTitle.setText(R.string.music_);
                    break;
                case Constant.TYPE_MOVIE:
                    holder.tv_mainTitle.setText(R.string.movie_);
                    holder.tv_subtitle.setVisibility(View.VISIBLE);
                    holder.tv_subtitle.setText("——《"+item.getSubtitle()+"》");
                    holder.layout_default.setBackgroundResource(R.drawable.feeds_movie);
                    holder.iv_image.setLayoutParams(holder.layoutParams);
                    break;
                default:
                    break;
            }

        }
        if(item.getContent_type().equals("4")){

            holder.layout_default.setVisibility(View.GONE);//默认布局
            holder.layout_music.setVisibility(View.VISIBLE);//music布局
            ImageLoader.displayImage(context,item.getImg_url(),holder.iv_cover);//加载圆形图片
            ImageLoader.displayImage(context,item.getAudio_platform_icon(),holder.iv_platform_icom);//左下角icon
            holder.tv_music_name.setText(item.getMusic_name()+" · "+item.getAudio_author()+" | "+item.getAudio_album());
            holder.iv_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.iv_play.setImageResource(isPlay?R.drawable.pause:R.drawable.play);
                    isPlay = isPlay?false:true;
                }
            });
        }else {
            ImageLoader.displayImage(context,item.getImg_url(),holder.iv_image,R.drawable.default_diary_pic);
        }
        holder.tv_title.setText(item.getTitle());
        holder.tv_author.setText(item.getAuthor().getUser_name());
        holder.tv_forward.setText(item.getForward());
        holder.tv_date.setText(DateUtils.getTodayDate(item.getPost_date()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //default
        private TextView tv_mainTitle;
        private TextView tv_title;
        private TextView tv_author;
        private ImageView iv_image;
        private TextView tv_forward;
        private TextView tv_date;
        private TextView tv_subtitle;
        private RelativeLayout layout_default;
        private RelativeLayout.LayoutParams layoutParams;

        //layout_music
        private LinearLayout layout_music;
        private ImageView iv_platform_icom;
        private ImageView iv_cover;
        private ImageView iv_play;
        private TextView tv_music_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_mainTitle = itemView.findViewById(R.id.tv_mainTitle);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_author = itemView.findViewById(R.id.tv_authorName);
            this.iv_image = itemView.findViewById(R.id.iv_image);
            this.tv_forward = itemView.findViewById(R.id.tv_forward);
            this.tv_date = itemView.findViewById(R.id.tv_date);
            this.tv_subtitle = itemView.findViewById(R.id.tv_subtitle);
            this.layout_default = itemView.findViewById(R.id.layout_default);
            this.layoutParams = (RelativeLayout.LayoutParams)iv_image.getLayoutParams();
            this.layoutParams.setMargins(0,50,0,50);

            this.layout_music = itemView.findViewById(R.id.layout_music);
            this.iv_platform_icom = itemView.findViewById(R.id.iv_audio_platform_icom);
            this.iv_cover = itemView.findViewById(R.id.iv_cover);
            this.iv_play = itemView.findViewById(R.id.iv_play);
            this.tv_music_name = itemView.findViewById(R.id.tv_music_name);

        }
    }
}
