package com.gnuey.one.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gnuey.one.R;
import com.gnuey.one.bean.LoadingEndBean;
import com.gnuey.one.utils.RxBus;
import com.gnuey.one.utils.ToastUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListFragment  extends LazyLoadFragment implements IBaseListView {

    public static final String TAG = "BaseListFragment";
    private TwinklingRefreshLayout twinklingRefreshLayout;
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

        twinklingRefreshLayout = view.findViewById(R.id.ly_twinkling);

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

//        observable = RxBus.getInstance().register(BaseListFragment.TAG);
//        observable.subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                adapter.notifyDataSetChanged();
//            }
//        });
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
    /**
     * 绑定生命周期
     */
    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
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
