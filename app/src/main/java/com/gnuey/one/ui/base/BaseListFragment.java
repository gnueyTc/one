package com.gnuey.one.ui.base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gnuey.one.InitApp;
import com.gnuey.one.R;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.RefreshHeader;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListFragment extends LazyLoadFragment implements IBaseListView {
    public static final String TAG = "BaseListFragment";
    @BindView(R.id.ly_twinkling)
    TwinklingRefreshLayout twinklingRefreshLayout;
    @BindView(R.id.recycle_view)
    protected RecyclerView recyclerView;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        Log.e(TAG, "initView: " );
        recyclerView.setHasFixedSize(true);
        twinklingRefreshLayout.setEnableLoadmore(false);
        twinklingRefreshLayout.setHeaderView(new RefreshHeader(InitApp.getApplication()));
        twinklingRefreshLayout.setTargetView(recyclerView);
        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                doOnRefresh();
            }
        });
    }



    @Override
    public void onShowLoading() {
        twinklingRefreshLayout.post(() -> twinklingRefreshLayout.startRefresh());
    }


    @Override
    public void fetchData() {
        onShowLoading();
        Log.e(TAG, "fetchData: ");

    }
    @Override
    public void onHideLoading() {
        twinklingRefreshLayout.post(() -> twinklingRefreshLayout.finishRefreshing());
    }


    @Override
    public void onShowNetError() {
        ToastUtils.showSingleToast(R.string.network_error);
    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(() -> {
            if (oldItems.size() > 0) {
                Items newItems = new Items(oldItems);
                newItems.remove(newItems.size() - 1);
                adapter.setItems(newItems);
                adapter.notifyDataSetChanged();
            } else if (oldItems.size() == 0) {
                adapter.setItems(oldItems);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
