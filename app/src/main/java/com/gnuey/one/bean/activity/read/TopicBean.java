package com.gnuey.one.bean.activity.read;

import java.util.List;

/**
 * Created by gnuey on 2018/12/17/017
 */
public class TopicBean {

    /**
     * id : 1430
     * category : 5
     * serials : []
     * source : topic
     * source_id : 99
     */

    private String id;
    private int category;
    private String source;
    private String source_id;
    private List<?> serials;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public List<?> getSerials() {
        return serials;
    }

    public void setSerials(List<?> serials) {
        this.serials = serials;
    }
}
