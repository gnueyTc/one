package com.gnuey.one.ui.base;

import java.util.List;

/**
 * Created by gnueyTc on 2018/4/28.
 */
public interface IBaseListView extends IBaseView  {
    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);


    /**
     * 刷新
     */
    void doOnRefresh();
}
