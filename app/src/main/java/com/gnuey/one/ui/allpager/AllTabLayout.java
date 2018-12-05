package com.gnuey.one.ui.allpager;

import android.view.View;
import com.gnuey.one.Register;
import com.gnuey.one.bean.allpage.AllDataBean;
import com.gnuey.one.component.AppComponent;
import com.gnuey.one.component.DaggerFragmentComponent;

import com.gnuey.one.ui.base.BaseListFragment;

import javax.inject.Inject;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnueyTc on 2018/7/11.
 */
public class AllTabLayout extends BaseListFragment implements AllPageContract.View{

    @Inject
    AllPresenter mPresenter;

    @Override
    protected void initView(View view) {
        super.initView(view);
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

    }


    @Override
    public void onSetAdapter(AllDataBean allDataBean) {
        oldItems.add(0,allDataBean.getAllListBean());
        oldItems.addAll(allDataBean.getList());
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clearData() {

    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
