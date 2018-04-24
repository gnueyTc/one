package com.gnuey.one.module.onepager.article;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnuey.one.R;
import com.gnuey.one.adapter.ArrayAdapter;
import com.gnuey.one.module.base.LazyLoadFragment;
import com.gnuey.one.widget.SinaRefreshHeader;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;

public class OneArticleView extends LazyLoadFragment{
    private RecyclerView recyclerView;
    private TwinklingRefreshLayout twinklingRefreshLayout;
    private int index;

    public OneArticleView(){

    }
    @SuppressLint("ValidFragment")
    public OneArticleView(int index){
        this.index = index;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int attachLayoutId() {
        return 0;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() throws NullPointerException {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_contens,container,false);
        recyclerView = view.findViewById(R.id.recycle_view);
        twinklingRefreshLayout = view.findViewById(R.id.ly_twinkling);
        twinklingRefreshLayout.setHeaderView(new SinaRefreshHeader(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList wha = new ArrayList();
        for(int  i = 0;i<5;i++){
            wha.add(new Object());
        }
        recyclerView.setAdapter(new ArrayAdapter(getActivity(),wha,index));
        return view;
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }
}
