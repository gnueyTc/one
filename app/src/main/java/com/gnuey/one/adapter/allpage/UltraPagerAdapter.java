package com.gnuey.one.adapter.allpage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.gnuey.one.R;
import com.gnuey.one.bean.allpage.AllListBean;
import com.gnuey.one.utils.ImageLoader;

import java.util.List;

/**
 * Created by gnuey on 2018/12/2/002
 */
public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;
    private List<AllListBean.DataBean> mList;
    private Context context;
    private int position;
    private OnClickListener onClickListener;
    public UltraPagerAdapter(List<AllListBean.DataBean> list,Context context,boolean isMultiScr) {
        this.mList = list;
        this.context = context;
        this.isMultiScr = isMultiScr;
    }
    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    @Override
    public int getCount() {
        return mList == null?0:mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.position = position;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_imageview, null);
        ImageView imageView = linearLayout.findViewById(R.id.iv_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
        ImageLoader.displayImage(context,mList.get(position).getCover(),imageView,R.drawable.default_diary_pic);
        container.addView(linearLayout);
        return linearLayout;
    }
    public int getPosition(){
        return position;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
    public interface OnClickListener{
        void onClick(int position);
    }
}
