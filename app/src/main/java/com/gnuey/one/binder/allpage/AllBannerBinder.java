package com.gnuey.one.binder.allpage;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.gnuey.one.R;
import com.gnuey.one.adapter.allpage.UltraPagerAdapter;
import com.gnuey.one.bean.allpage.AllListBean;
import com.gnuey.one.ui.activity.read.ReadActivity;
import com.gnuey.one.utils.Constant;
import com.tmall.ultraviewpager.UltraViewPager;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnuey on 2018/12/2/002
 */
public class AllBannerBinder extends ItemViewBinder<AllListBean,AllBannerBinder.ViewHolder> {
    private final String source = "source";
    @NonNull
    @Override
    protected AllBannerBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_banner,parent,false);
        return new AllBannerBinder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull AllBannerBinder.ViewHolder holder, @NonNull AllListBean item) {
        final Context context = holder.itemView.getContext();
        holder.ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        PagerAdapter adapter = new UltraPagerAdapter(item.getData(),context,false);
        holder.ultraViewPager.setAdapter(adapter);
        //内置indicator初始化
        holder.ultraViewPager.initIndicator();
        //设置indicator样式
        holder.ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(R.drawable.bg_white)
                .setNormalColor(R.drawable.bg_gray)
                .setRadius((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics()));
        //设置indicator对齐方式
        holder.ultraViewPager.getIndicator()
                .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP | Gravity.RIGHT)
                .setMargin(0,50,50,0);
        //构造indicator,绑定到UltraViewPager
        holder.ultraViewPager.getIndicator().build();
        //设定页面循环播放
        holder.ultraViewPager.setInfiniteLoop(true);
        //设定页面自动切换  间隔2秒
        holder.ultraViewPager.setAutoScroll(4000);
        ((UltraPagerAdapter) adapter).setOnClickListener(new UltraPagerAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                AllListBean.DataBean dataBean = item.getData().get(position);
                ReadActivity.startReadDetailActivity(context,new String[]{Constant.getType(String.valueOf(dataBean.getCategory())),dataBean.getContent_id(),source,String.valueOf(dataBean.getId())});
                Log.e("AllBannerBinder", "onClick: " + position);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
//        private AutoScrollViewPager<CircleViewPagerAdapter> vp_auto_viewpager;
        private UltraViewPager ultraViewPager;
        public ViewHolder(View itemView) {
            super(itemView);
//            this.vp_auto_viewpager = itemView.findViewById(R.id.vp_auto_viewpager);
            this.ultraViewPager = itemView.findViewById(R.id.vp_ultra);
        }
    }
}
