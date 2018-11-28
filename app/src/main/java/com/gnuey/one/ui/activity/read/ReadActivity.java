package com.gnuey.one.ui.activity.read;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gnuey.one.InitApp;
import com.gnuey.one.R;
import com.gnuey.one.Register;
import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.bean.activity.read.ReadActivityBean;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.component.DaggerActivityComponent;
import com.gnuey.one.ui.base.BaseActivity;
import com.gnuey.one.utils.AdapterDiffCallBack;
import com.gnuey.one.utils.ToastUtils;
import com.gnuey.one.widget.CsWebView;
import com.gnuey.one.widget.RefreshHeader;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnuey on 2018/11/26/026
 */
public class ReadActivity extends BaseActivity implements ReadContract.View{
    @Inject
    ReadPresenter mPresenter;

    @BindView(R.id.ly_twinkling)
    TwinklingRefreshLayout twinklingRefreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    private final static String TAG = ReadActivity.class.getSimpleName();
    private MultiTypeAdapter adapter;
    private Items oldItems = new Items();
    private String[] array;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppComponent();
        mPresenter.attachView(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        twinklingRefreshLayout.setEnableRefresh(false);
        twinklingRefreshLayout.setEnableLoadmore(true);
        twinklingRefreshLayout.setHeaderView(new RefreshHeader(mContext));
        twinklingRefreshLayout.setTargetView(recyclerView);
        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mPresenter.getCommontData(array[0],array[1]);
            }
        });
        adapter = new MultiTypeAdapter();
        Register.registerReadActivityItem(adapter);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_list;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        array = bundle.getStringArray(TAG);
        if(array != null && array.length == 2){
            mPresenter.getReadData(array[0],array[1]);
            mPresenter.getCommontData(array[0],array[1]);
        }

    }

    private void setAppComponent(){
        DaggerActivityComponent.builder()
                .appComponent(InitApp.getApplication().getAppComponent())
                .build()
                .inject(this);
    }
    public static void startReadDetailActivity(Context context,String[] content){
        Intent intent = new Intent(context,ReadActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArray(TAG,content);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }


    @Override
    public void doSetCommontAdapter(List<?> list) {
        oldItems.addAll(list);
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();
        twinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void doSetReadData(ReadActivityBean data) {
        oldItems.add(0,data.getWebBean());
        oldItems.add(1,data.getAuthorDataBean());
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {
        twinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void onShowNoMore() {
        twinklingRefreshLayout.finishLoadmore();
        ToastUtils.showSingleToast(R.string.no_more_content);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
