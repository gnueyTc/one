package com.gnuey.one.ui.base;

import android.os.Bundle;
import android.util.Log;


import androidx.annotation.Nullable;


public abstract class LazyLoadFragment extends BaseFragment  {
    /**
     * 是否初始化过布局
     */
    protected boolean isViewInitiated;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是否加载过数据
     */
    protected boolean isDataInitiated;
    /**
     * 是否启用懒加载
     */

    protected boolean isAbleToLoad;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        Log.e("LazyLoad", "setUserVisibleHint: "+isVisibleToUser );
        if(isVisibleToUser){
            prepareFetchData();
        }else {
            clearData();
        }

    }

    /**
     * 懒加载
     */
    public abstract void fetchData();
    public abstract void clearData();

    /**
     * 判断懒加载条件
     *
     */
    public void prepareFetchData() {
        if (isVisibleToUser && isViewInitiated && isAbleToLoad) {
            Log.e("LazyLoad", "prepareFetchData: " );
            fetchData();
            isDataInitiated = true;
        }
    }
}
