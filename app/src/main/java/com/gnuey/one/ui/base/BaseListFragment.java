package com.gnuey.one.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gnuey.one.R;
import com.gnuey.one.bean.LoadingEndBean;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.SinaRefreshHeader;
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
        twinklingRefreshLayout.setHeaderView(new SinaRefreshHeader(getContext()));


    }

    @Override
    public void onShowLoading() {
        twinklingRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                twinklingRefreshLayout.startRefresh();
            }
        });
    }


    @Override
    public void fetchData() {
        Log.e(TAG, "fetchData: ");

    }
    @Override
    public void onHideLoading() {
        twinklingRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                twinklingRefreshLayout.finishRefreshing();
            }
        });
    }


    @Override
    public void onShowNetError() {
        ToastUtils.showSingleToast(R.string.network_error);
    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (oldItems.size() > 0) {
                    Items newItems = new Items(oldItems);
                    newItems.remove(newItems.size() - 1);
                    newItems.add(new LoadingEndBean());
                    adapter.setItems(newItems);
                    adapter.notifyDataSetChanged();
                } else if (oldItems.size() == 0) {
                    oldItems.add(new LoadingEndBean());
                    adapter.setItems(oldItems);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
