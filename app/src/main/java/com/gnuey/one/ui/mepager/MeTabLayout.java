package com.gnuey.one.ui.mepager;

import android.view.View;
import android.widget.LinearLayout;

import com.gnuey.one.R;
import com.gnuey.one.ui.base.BaseFragment;
import com.gnuey.one.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gnueyTc on 2018/7/11.
 */
public class MeTabLayout extends BaseFragment{
    @OnClick(R.id.me_layout)
    void onClick(){
        ToastUtils.showSingleToast("施工中···");
    }
    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() throws NullPointerException {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void onShowNoMore() {

    }
}
