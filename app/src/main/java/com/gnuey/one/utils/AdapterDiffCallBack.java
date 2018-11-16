package com.gnuey.one.utils;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;



public class AdapterDiffCallBack<K extends List> extends DiffUtil.Callback{

    private final K mOldItems, mNewItems;

    private AdapterDiffCallBack(K mOldItems,K mNewItems){
        this.mOldItems = mOldItems;
        this.mNewItems = mNewItems;
    }

    public static <T extends RecyclerView.Adapter,K extends List>void create(K oldList, K newList, T adapter){
        AdapterDiffCallBack<K> adapterDiffCallBack = new <K>AdapterDiffCallBack(oldList,newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(adapterDiffCallBack,true);
        result.dispatchUpdatesTo(adapter);
    }

    @Override
    public int getOldListSize() {
        return mOldItems==null?0:mOldItems.size();
    }

    @Override
    public int getNewListSize() {
        return mNewItems==null?0:mNewItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).hashCode() == mNewItems.get(newItemPosition).hashCode();
    }
}
