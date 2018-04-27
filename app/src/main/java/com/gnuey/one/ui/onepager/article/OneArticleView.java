package com.gnuey.one.ui.onepager.article;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnuey.one.R;
import com.gnuey.one.adapter.ArrayAdapter;
import com.gnuey.one.bean.LoadingBean;
import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.utils.AdapterDiffCallBack;
import com.gnuey.one.widget.SinaRefreshHeader;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class OneArticleView extends BaseListFragment implements OneArticleContract.View{
    public static final String TAG = "OneArticleView";

    @Inject
    OneArticlePresenter mPresenter;
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
    protected void initView(View view) {
        super.initView(view);
        mPresenter.attachView(this);
        adapter = new MultiTypeAdapter(oldItems);

    }

    @Override
    protected void initData() {
//        mPresenter.doLoadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
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
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        AdapterDiffCallBack.create(oldItems,newItems,adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        recyclerView.stopScroll();
    }



    @Override
    public void onLoadData() {

    }

    @Override
    public void onRefresh() {

    }
}
