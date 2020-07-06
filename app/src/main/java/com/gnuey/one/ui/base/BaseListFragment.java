package com.gnuey.one.ui.base;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gnuey.one.R;
import com.gnuey.one.utils.NetWorkUtil;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.RefreshHeader;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListFragment extends LazyLoadFragment implements IBaseListView {
    public static final String TAG = BaseListFragment.class.getSimpleName();
    @BindView(R.id.ly_twinkling)
    TwinklingRefreshLayout twinklingRefreshLayout;
    @BindView(R.id.recycle_view)
    protected RecyclerView recyclerView;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();

    @Override
    protected int attachLayoutId() {
        return R.layout.base_list_toolbar;
    }

    @Override
    protected void initView(View view) {
        Log.e(TAG, "initView: " );
        recyclerView.setHasFixedSize(true);
        twinklingRefreshLayout.setEnableLoadmore(false);
        twinklingRefreshLayout.setHeaderView(new RefreshHeader(mContext));
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
//        RxBus.getInstance().post(TAG,false);
//        doOnRefresh();
        Log.e(TAG, "fetchData: ");

    }

    @Override
    public void doOnRefresh() {
//        RxBus.getInstance().post(TAG,true);
    }

    @Override
    public void onHideLoading() {
        twinklingRefreshLayout.post(() -> twinklingRefreshLayout.finishRefreshing());
    }


    @Override
    public void onShowNetError() {
        if(NetWorkUtil.isNetworkConnected(mContext)){
            ToastUtils.showSingleToast(R.string.network_error);
        }
        ToastUtils.showSingleToast(R.string.no_network);
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
