package com.gnuey.one.ui.onepager.article;


import android.os.Handler;
import android.util.Log;
import android.view.View;



import com.gnuey.one.Register;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;
import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.utils.AdapterDiffCallBack;
import com.gnuey.one.utils.GlideApp;
import com.gnuey.one.utils.ImageLoader;
import com.gnuey.one.utils.RxBus;


import java.util.List;

import javax.inject.Inject;


import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class OneArticleView extends BaseListFragment implements OneArticleContract.View {
    @Inject
    OneArticlePresenter mPresenter;

    public static final String TAG = OneArticleView.class.getSimpleName();
    private String date = "0";

    public OneArticleView getDate(String date){
        this.date = date;
        Log.e(TAG, "initData: date ="+ date );
        return this;
    }
    public void setUnableToLazyLoad(boolean isUnable){
        isUnableToLoad = isUnable;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        Log.e(TAG, "initView: ");
        mPresenter.attachView(this);
        oldItems.clear();//因为viewPager缓存原因，当复用到此view时候必须清空，不然会跟第一次加载的数据重叠
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerOneArticleItem(adapter);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
//        code = Integer.valueOf(getArguments().getString(OneArticleView.TAG));
//        code = getArguments().getInt(OneArticleView.TAG);
    }

    @Override
    public void clearData() {
//        Log.e(TAG, "clearData: " );
        if(mContext!=null){
            ImageLoader.clearMemory(GlideApp.get(mContext));
        }

    }

    @Override
    protected void setAppComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }


    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items(list);
        AdapterDiffCallBack.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        recyclerView.stopScroll();

    }

    @Override
    public void fetchData() {
        super.fetchData();
        if("0".equals(date)){
            RxBus.getInstance().post(BaseListFragment.TAG,true);
        }
        mPresenter.doLoadData(date);
        Log.e(TAG, "fetchData: "+date);
    }

    @Override
    public void doOnRefresh() {
        super.doOnRefresh();
        mPresenter.doRefresh(date);
        Log.e(TAG, "doOnRefresh: "+date);
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
//        RxBus.getInstance().unregisterAll();
        super.onDestroy();
        Log.e(TAG, "OneArticleView: onDestroy" );
    }
}
