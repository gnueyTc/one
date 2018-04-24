package com.gnuey.one.module.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gnuey.one.R;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

public abstract class BaseListFragment extends LazyLoadFragment  {
    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_one_contens;
    }

    @Override
    protected void initView(View view) {
       recyclerView = view.findViewById(R.id.recycle_view);

       refreshLayout = view.findViewById(R.id.ly_twinkling);

    }

    @Override
    public void onShowLoading() {
       refreshLayout.post(new Runnable() {
           @Override
           public void run() {
               refreshLayout.startRefresh();
           }
       });
    }

    @Override
    public void onHideLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishRefreshing();
            }
        });
    }

    @Override
    public void onShowNetError() {

    }


}
