package com.gnuey.one.binder.onepager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnuey.one.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by gnueyTc on 2018/5/29.
 */
public class OneArticleViewMenuBinder extends ItemViewBinder {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.test,parent,false);
        return new OneArticleViewMenuBinder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Object item) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
