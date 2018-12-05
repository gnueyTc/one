package com.gnuey.one.bean.allpage;

import java.util.List;

/**
 * Created by gnuey on 2018/12/2/002
 */
public class AllDataBean {
    AllListBean allListBean;
    List<AllListBean.DataBean> list;

    public AllListBean getAllListBean() {
        return allListBean;
    }

    public void setAllListBean(AllListBean allListBean) {
        this.allListBean = allListBean;
    }

    public List<AllListBean.DataBean> getList() {
        return list;
    }

    public void setList(List<AllListBean.DataBean> list) {
        this.list = list;
    }
}
