package com.gnuey.one.ui.activity.read;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.gnuey.one.InitApp;
import com.gnuey.one.R;
import com.gnuey.one.Register;
import com.gnuey.one.bean.activity.read.PlayAudioBean;
import com.gnuey.one.bean.activity.read.ReadActivityBean;
import com.gnuey.one.binder.activity.ReadActivityWebViewBinder;
import com.gnuey.one.component.DaggerActivityComponent;
import com.gnuey.one.ui.base.BaseActivity;
import com.gnuey.one.utils.RxBus;
import com.gnuey.one.utils.ToastUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
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

    @BindView(R.id.iv_play)
    ImageView imageView;
    private final static String TAG = ReadActivity.class.getSimpleName();
    private MultiTypeAdapter adapter;
    private Items oldItems = new Items();
    private String[] array;
    private Flowable<String> playAudioBeanObservable;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppComponent();
        mPresenter.attachView(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        twinklingRefreshLayout.setEnableRefresh(false);
        twinklingRefreshLayout.setEnableLoadmore(true);
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
        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_audio_loading);
        initData();
        playAudioBeanObservable = RxBus.getInstance().register(ReadActivityWebViewBinder.TAG,String.class);
        playAudioBeanObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String url){
                if(url.equals(ReadActivityWebViewBinder.STOP_PLAYING)){
                    playService.stop();
                    playImage(false);
                }else {
                    playService.play(url);
                    playImage(true);
                }
                Log.e(TAG, "accept: "+ url);
            }
        });
    }
    private void playImage(boolean isPlaying){
        if(isPlaying){
            imageView.setImageDrawable(animationDrawable);
            animationDrawable.start();
        }else {
            imageView.setImageResource(R.drawable.float_player_pause);
            animationDrawable.stop();
        }
    }
    private void initData(){
        Bundle bundle = getIntent().getExtras();
        array = bundle.getStringArray(TAG);
        if(array != null && array.length == 4){
            mPresenter.getReadData(array[0],array[1],array[2],array[3]);
            mPresenter.getCommontData(array[0],array[1]);
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }


    @Override
    protected void onResume() {
        super.onResume();

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
        Items items = new Items(oldItems);
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
        twinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void doSetReadData(ReadActivityBean data) {
        oldItems.add(0,data.getWebBean());
        if(data.getAuthorDataBean()!=null){
            oldItems.add(1,data.getAuthorDataBean());
        }
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onShowLoading() {
    }

    @Override
    public void onHideLoading() {
        twinklingRefreshLayout.finishLoadmore();
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
        RxBus.getInstance().unRegisterEach(ReadActivityWebViewBinder.TAG);
        mPresenter.detachView();
    }
}
