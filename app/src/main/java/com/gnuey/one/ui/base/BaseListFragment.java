package com.gnuey.one.ui.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gnuey.one.R;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.SinaRefreshHeader;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import io.reactivex.Observable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListFragment extends LazyLoadFragment implements IBaseListView {

    public static final String TAG = "BaseListFragment";
    private TwinklingRefreshLayout twinklingRefreshLayout;
    protected RecyclerView recyclerView;
    protected Observable<String> observable;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();
    protected boolean canLoadMore = false;
    private int index;
    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        Log.e(TAG, "initView: " );
        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        twinklingRefreshLayout = view.findViewById(R.id.ly_twinkling);
        twinklingRefreshLayout.setEnableLoadmore(false);
        twinklingRefreshLayout.setHeaderView(new SinaRefreshHeader(getContext()));
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
