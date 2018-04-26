package com.gnuey.one.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gnuey.one.R;
import com.gnuey.one.bean.LoadingEndBean;
import com.gnuey.one.utils.RxBus;
import com.gnuey.one.utils.ToastUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListFragment extends LazyLoadFragment  {

    public static final String TAG = "BaseListFragment";
    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    protected Observable<Integer> observable;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();
    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
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
    public void fetchData() {
        observable = RxBus.getInstance().register(BaseListFragment.TAG);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

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
        ToastUtils.showSingleToast(R.string.network_error);
    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(oldItems.size() > 0){
                    Items newItems = new Items(oldItems);
                    newItems.remove(newItems.size() - 1);
                    newItems.add(new LoadingEndBean());
                    adapter.setItems(newItems);
                    adapter.notifyDataSetChanged();
                }else if(oldItems.size()==0){
                    oldItems.add(new LoadingEndBean());
                    adapter.setItems(oldItems);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
