package com.gnuey.one.binder.onepager;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gnuey.one.R;
import com.gnuey.one.Register;
import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.utils.ExpandAnimationUtil;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnueyTc on 2018/5/29.
 */
public class OneArticleViewMenuBinder extends ItemViewBinder<OneFlattenBean,OneArticleViewMenuBinder.ViewHolder> {
    private static final String TAG = "MenuBinder";
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_menu,parent,false);
        return new OneArticleViewMenuBinder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OneFlattenBean item) {

        holder.tv_title.setText("一个VOL."+item.getMenu().getVol());
        holder.adapter.setItems(item.getMenu().getList());
        holder.expandAnimationUtil.setExpanHeight(item.getMenu().getList().size()*80);
        holder.itemView.setOnClickListener(v -> {
            //第三个参数height为recyclerView的每个item预留80的高度
            holder.expandAnimationUtil.taggle();
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private MultiTypeAdapter adapter;
        private TextView tv_title;
        private ImageView img_arrow;
        private ExpandAnimationUtil expandAnimationUtil;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.img_arrow = itemView.findViewById(R.id.img_arrow);
            this.recyclerView = itemView.findViewById(R.id.recycle_view);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            this.adapter = new MultiTypeAdapter();
            Register.registerOneArticleMenuItem(adapter);
            this.recyclerView.setAdapter(adapter);

            this.expandAnimationUtil = ExpandAnimationUtil.getIntance(itemView.getContext(),this.img_arrow);
            this.expandAnimationUtil.setTagerView(this.recyclerView);
            Log.e(TAG, "ViewHolder: Not bind ryViewHeight = "+adapter.getItemCount() );
            this.tv_title.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        }
    }
}
