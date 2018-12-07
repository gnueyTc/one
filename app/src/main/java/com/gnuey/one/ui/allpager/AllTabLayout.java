package com.gnuey.one.ui.allpager;

import android.graphics.Typeface;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.gnuey.one.R;
import com.gnuey.one.Register;
import com.gnuey.one.bean.allpage.AllDataBean;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;

import com.gnuey.one.ui.base.BaseListFragment;
import com.gnuey.one.utils.AdapterDiffCallBack;


import javax.inject.Inject;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnueyTc on 2018/7/11.
 */
public class AllTabLayout extends BaseListFragment implements AllPageContract.View{

    @Inject
    AllPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_tittle)
    TextView mTittle;
    @Override
    protected void initView(View view) {
        super.initView(view);
        toolbar.setVisibility(View.VISIBLE);
        mPresenter.attachView(this);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerAllTabLayoutItem(adapter);
        recyclerView.setAdapter(adapter);

    }
    @Override
    protected void setAppComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initData() throws NullPointerException {
        mPresenter.doLoadData("0");
        mTittle.setText("ONE    IS    ALL");
        mTittle.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"one_title.otf"));//自定义字体
    }


    @Override
    public void onSetAdapter(AllDataBean allDataBean) {
        Items newItems = new Items(allDataBean.getList());
        newItems.add(0,allDataBean.getAllListBean());
        Log.e(TAG, "onSetAdapter: oldItems = " + oldItems.size() + " newItems = " + newItems.size() );
        AdapterDiffCallBack.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
    }

    @Override
    public void clearData() {

    }

    @Override
    public void doOnRefresh() {
        super.doOnRefresh();
        mPresenter.doLoadData("0");
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
