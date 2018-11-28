package com.gnuey.one.bean.activity.read;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gnuey on 2018/11/24/024
 */
public class OneHtmlContentBean {

    @SerializedName("res")
    private int _$Res213; // FIXME check this code
    private DataBean data;

    public int get_$Res213() {
        return _$Res213;
    }

    public void set_$Res213(int _$Res213) {
        this._$Res213 = _$Res213;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("audio")
        private String _$Audio177; // FIXME check this code
        private String anchor;
        private int category;
        private String id;
        private String title;
        private String web_url;
        private ShareListBean share_list;
        private String html_content;
        private int praisenum;
        private int commentnum;
        private List<AuthorListBean> author_list;
        private List<TagListBean> tag_list;

        public String get_$Audio177() {
            return _$Audio177;
        }

        public void set_$Audio177(String _$Audio177) {
            this._$Audio177 = _$Audio177;
        }

        public String getAnchor() {
            return anchor;
        }

        public void setAnchor(String anchor) {
            this.anchor = anchor;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public ShareListBean getShare_list() {
            return share_list;
        }

        public void setShare_list(ShareListBean share_list) {
            this.share_list = share_list;
        }

        public String getHtml_content() {
            return html_content;
        }

        public void setHtml_content(String html_content) {
            this.html_content = html_content;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public List<AuthorListBean> getAuthor_list() {
            return author_list;
        }

        public void setAuthor_list(List<AuthorListBean> author_list) {
            this.author_list = author_list;
        }

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class ShareListBean {
            /**
             * wx : {"title":"ONE STORY | 吹笛子的人","desc":"文/李濛 好像出走也是一种戒不掉的瘾。","link":"http://m.wufazhuce.com/article/3574?channel=singlemessage","imgUrl":"http://image.wufazhuce.com/ONE_logo_120_square.png","audio":""}
             * wx_timeline : {"title":"ONE STORY | 吹笛子的人","desc":"文/李濛 好像出走也是一种戒不掉的瘾。","link":"http://m.wufazhuce.com/article/3574?channel=timeline","imgUrl":"http://image.wufazhuce.com/ONE_logo_120_square.png","audio":""}
             * weibo : {"title":"ONE一个《ONE STORY | 吹笛子的人》 文/李濛： 好像出走也是一种戒不掉的瘾。 阅读全文：http://m.wufazhuce.com/article/3574?channel=weibo 下载ONE一个APP:http://weibo.com/p/100404157874","desc":"","link":"http://m.wufazhuce.com/article/3574?channel=weibo","imgUrl":"","audio":""}
             * qq : {"title":"吹笛子的人","desc":"好像出走也是一种戒不掉的瘾。","link":"http://m.wufazhuce.com/article/3574?channel=qq","imgUrl":"http://image.wufazhuce.com/ONE_logo_120_square.png","audio":""}
             */

            private WxBean wx;
            private WxTimelineBean wx_timeline;
            private WeiboBean weibo;
            private QqBean qq;

            public WxBean getWx() {
                return wx;
            }

            public void setWx(WxBean wx) {
                this.wx = wx;
            }

            public WxTimelineBean getWx_timeline() {
                return wx_timeline;
            }

            public void setWx_timeline(WxTimelineBean wx_timeline) {
                this.wx_timeline = wx_timeline;
            }

            public WeiboBean getWeibo() {
                return weibo;
            }

            public void setWeibo(WeiboBean weibo) {
                this.weibo = weibo;
            }

            public QqBean getQq() {
                return qq;
            }

            public void setQq(QqBean qq) {
                this.qq = qq;
            }

            public static class WxBean {
                /**
                 * title : ONE STORY | 吹笛子的人
                 * desc : 文/李濛 好像出走也是一种戒不掉的瘾。
                 * link : http://m.wufazhuce.com/article/3574?channel=singlemessage
                 * imgUrl : http://image.wufazhuce.com/ONE_logo_120_square.png
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }

            public static class WxTimelineBean {
                /**
                 * title : ONE STORY | 吹笛子的人
                 * desc : 文/李濛 好像出走也是一种戒不掉的瘾。
                 * link : http://m.wufazhuce.com/article/3574?channel=timeline
                 * imgUrl : http://image.wufazhuce.com/ONE_logo_120_square.png
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }

            public static class WeiboBean {
                /**
                 * title : ONE一个《ONE STORY | 吹笛子的人》 文/李濛： 好像出走也是一种戒不掉的瘾。 阅读全文：http://m.wufazhuce.com/article/3574?channel=weibo 下载ONE一个APP:http://weibo.com/p/100404157874
                 * desc :
                 * link : http://m.wufazhuce.com/article/3574?channel=weibo
                 * imgUrl :
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }

            public static class QqBean {
                /**
                 * title : 吹笛子的人
                 * desc : 好像出走也是一种戒不掉的瘾。
                 * link : http://m.wufazhuce.com/article/3574?channel=qq
                 * imgUrl : http://image.wufazhuce.com/ONE_logo_120_square.png
                 * audio :
                 */

                private String title;
                private String desc;
                private String link;
                private String imgUrl;
                private String audio;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getAudio() {
                    return audio;
                }

                public void setAudio(String audio) {
                    this.audio = audio;
                }
            }
        }

        public static class AuthorListBean {
            /**
             * user_id : 8604531
             * user_name : 李濛
             * desc : 写小说、剧本、影评等。居无定所，浪迹天涯。
             * wb_name :
             * is_settled : 0
             * settled_type : 0
             * summary : 半吊子文案，菜鸟编剧，小说初学者
             * fans_total : 5837
             * web_url : http://image.wufazhuce.com/Fr5ckkGRNmKOCN0vGB31b-VhXaga
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

        public static class TagListBean {
            /**
             * id : 7
             * title : ONE STORY
             */

            private String id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
