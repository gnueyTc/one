package com.gnuey.one.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnuey.one.R;

import java.util.List;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.ViewHolders>{
    private Context context;
    private LayoutInflater layoutInflater;
    private List dataList;
    private int index;
    public ArrayAdapter(Context context, List dataList,int index){
        this.dataList = dataList;
        this.index = index;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_one,parent,false);
        return new ViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        holder.textView.setText(""+index);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolders extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolders(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);

        }
    }
}
