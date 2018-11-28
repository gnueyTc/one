package com.gnuey.one.bean.activity.read;

/**
 * Created by gnuey on 2018/11/28/028
 */
public class ReadActivityBean {
    private WebBean webBean;
    private AuthorBean.DataBean authorDataBean;

    public WebBean getWebBean() {
        return webBean;
    }

    public void setWebBean(WebBean webBean) {
        this.webBean = webBean;
    }

    public AuthorBean.DataBean getAuthorDataBean() {
        return authorDataBean;
    }

    public void setAuthorDataBean(AuthorBean.DataBean authorDataBean) {
        this.authorDataBean = authorDataBean;
    }

    public CommentBean getCommentBean() {
        return commentBean;
    }

    public void setCommentBean(CommentBean commentBean) {
        this.commentBean = commentBean;
    }

    private CommentBean commentBean;
}
