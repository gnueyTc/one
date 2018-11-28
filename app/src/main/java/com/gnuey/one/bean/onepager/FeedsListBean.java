package com.gnuey.one.bean.onepager;

import java.util.List;

/**
 * Created by gnuey on 2018/11/9/009
 */
public class FeedsListBean {

    /**
     * res : 0
     * data : [{"id":16662,"date":"2018-11-09","cover":""},{"id":16623,"date":"2018-11-08","cover":"http://image.wufazhuce.com/FqGCOPCuEVACXX-2lqNcuUmzcWgl"},{"id":16621,"date":"2018-11-07","cover":"http://image.wufazhuce.com/Ftf0AvnBWr7WLk8oPUW-H7zBEZUf"},{"id":16619,"date":"2018-11-06","cover":"http://image.wufazhuce.com/FotCjChgljMwJkIau9A3LBhoBiyC"},{"id":16617,"date":"2018-11-05","cover":"http://image.wufazhuce.com/Fj06el5x-lNvctTaQp_DT8mDd715"},{"id":16615,"date":"2018-11-04","cover":"http://image.wufazhuce.com/FhiVdSeYEo8Pu7yS2Gu-GwnaKkvR"},{"id":16614,"date":"2018-11-03","cover":"http://image.wufazhuce.com/FqCW8TpOXYXPPWbSvx9185vjf34Y"},{"id":16612,"date":"2018-11-02","cover":"http://image.wufazhuce.com/FqIXhl149Pj1AftgRFBOxiC2MXwz"},{"id":16607,"date":"2018-11-01","cover":"http://image.wufazhuce.com/FuBI0qoaF1rXYUv7z4eXWs5nyXKZ"}]
     */

    private int res;
    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 16662
         * date : 2018-11-09
         * cover :
         */

        private int id;
        private String date;
        private String cover;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
