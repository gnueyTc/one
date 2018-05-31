package com.gnuey.one.utils;

import com.gnuey.one.bean.onepager.OneFlattenBean;


import java.util.List;

/**
 * Created by gnueyTc on 2018/5/31.
 */
public class OneViewNavigationData {
    public static OneFlattenBean getNavigationData(List<OneFlattenBean> list){
        OneFlattenBean flattenBean = list.get(1);
        flattenBean.setCategory("-1");
        return flattenBean;
    }
}
