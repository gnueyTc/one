package com.gnuey.one.bean.activity.read;

import java.util.List;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class AuthorBean {

    /**
     * res : 0
     * data : [{"user_id":"9089200","user_name":"江不泊","desc":"所以，你幸福吗？不要回答我。","wb_name":"","is_settled":"0","settled_type":"0","summary":"所以，你幸福吗？不要回答我。","fans_total":"3773","web_url":"http://image.wufazhuce.com/Fl0df-t58h4ch6iZsNYlhy4dAVV-"}]
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
         * user_id : 9089200
         * user_name : 江不泊
         * desc : 所以，你幸福吗？不要回答我。
         * wb_name :
         * is_settled : 0
         * settled_type : 0
         * summary : 所以，你幸福吗？不要回答我。
         * fans_total : 3773
         * web_url : http://image.wufazhuce.com/Fl0df-t58h4ch6iZsNYlhy4dAVV-
         */

        private String user_id;
        private String user_name;
        private String desc;
        private String wb_name;
        private String is_settled;
        private String settled_type;
        private String summary;
        private String fans_total;
        private String web_url;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getWb_name() {
            return wb_name;
        }

        public void setWb_name(String wb_name) {
            this.wb_name = wb_name;
        }

        public String getIs_settled() {
            return is_settled;
        }

        public void setIs_settled(String is_settled) {
            this.is_settled = is_settled;
        }

        public String getSettled_type() {
            return settled_type;
        }

        public void setSettled_type(String settled_type) {
            this.settled_type = settled_type;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getFans_total() {
            return fans_total;
        }

        public void setFans_total(String fans_total) {
            this.fans_total = fans_total;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }
}
