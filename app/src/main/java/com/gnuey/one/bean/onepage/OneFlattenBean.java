package com.gnuey.one.bean.onepage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by gnueyTc on 2018/5/31.
 */
public class OneFlattenBean implements Parcelable {
    /**
     * id : 15424
     * category : 0
     * display_category : 6
     * item_id : 2092
     * title : 摄影
     * forward : 爱你，就像吃蘸盐的面包，像在夜里狂热地疾走再将嘴唇凑近水龙头，像打开没有标签的沉重包裹，焦急、愉快、小心。
     * img_url : http://image.wufazhuce.com/FkbOujesR6G41UIbq9hI4LhXCh2y
     * like_count : 5185
     * post_date : 2018-05-30 06:00:00
     * last_update_date : 2018-05-28 12:49:56
     * author : {"user_id":"8741452","user_name":"粟冰箱","desc":"一台以鬼故事制冷的电器精。","wb_name":"","is_settled":"0","settled_type":"0","summary":"一台以鬼故事制冷的电器精。","fans_total":"1893","web_url":"http://image.wufazhuce.com/FjKfi58DFi5Xrwnc7a6Ac8DDTIEI"}
     * video_url :
     * audio_url :
     * audio_platform : 2
     * start_video :
     * has_reading : 0
     * volume : VOL.2062
     * pic_info : Wira Dyatmika
     * words_info : 纳齐姆·希克梅特
     * subtitle :
     * number : 0
     * serial_id : 0
     * serial_list : []
     * movie_story_id : 0
     * ad_id : 0
     * ad_type : 0
     * ad_pvurl :
     * ad_linkurl :
     * ad_makettime :
     * ad_closetime :
     * ad_share_cnt :
     * ad_pvurl_vendor :
     * content_id : 2092
     * content_type : 0
     * content_bgcolor : #FFFFFF
     * share_url : http://m.wufazhuce.com/one/2092
     * share_info : {"url":"http://m.wufazhuce.com/one/2092","image":"http://image.wufazhuce.com/FkbOujesR6G41UIbq9hI4LhXCh2y","title":"VOL.2062","content":"爱你，就像吃蘸盐的面包，像在夜里狂热地疾走再将嘴唇凑近水龙头，像打开没有标签的沉重包裹，焦急、愉快、小心。"}
     * share_list : {"wx":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=singlemessage","imgUrl":"","audio":""},"wx_timeline":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=timeline","imgUrl":"","audio":""},"weibo":{"title":"ONE一个 爱你，就像吃蘸盐的面包，像在夜里狂热地疾走再将嘴唇凑近水龙头，像打开没有标签的沉重包裹，焦急、愉快、小心。\u2014\u2014纳齐姆·希克梅特 下载ONE一个APP:http://weibo.com/p/100404157874","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=weibo","imgUrl":"","audio":""},"qq":{"title":"","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=qq","imgUrl":"","audio":""}}
     * tag_list : [{"id":"7","title":"ONE STORY"}]
     * orientation : 0
     * answerer : {"user_id":"7468492","user_name":"昔央","desc":"关于小说家的唯一道德，就是吃下这个世界的噩梦。微信公众号：夕阳下的武士（ID ：hyydnsz ）","wb_name":"@昔央","is_settled":"0","settled_type":"0","summary":"作者，编剧","fans_total":"8245","web_url":"http://image.wufazhuce.com/FhJx82FwP9t8GKsEI284bhmtXPus"}
     * music_name : 灯塔
     * audio_platform_icon : http://image.wufazhuce.com/music_copyright_2_2.png
     * audio_platform_name : ONE·一个
     * audio_author : 侯康
     * audio_album : 灯塔
     * cover : http://image.wufazhuce.com/Fk0TjFumOLrF-AEos7-qjsht1bcA?imageMogr2/thumbnail/908%7Cwatermark/1/image/aHR0cDovL2ltYWdlLnd1ZmF6aHVjZS5jb20vbXVzaWNfY29weXJpZ2h0XzIucG5n/gravity/East
     * bg_color : #000
     */

    private String dataId;
    private String date;
    private String id;
    private String category;
    private int display_category;
    private String item_id;
    private String title;
    private String forward;
    private String img_url;
    private int like_count;
    private String post_date;
    private String last_update_date;
    private AuthorBean author;
    private String video_url;
    private String audio_url;
    private int audio_platform;
    private String start_video;
    private int has_reading;
    private String volume;
    private String pic_info;
    private String words_info;
    private String subtitle;
    private int number;
    private int serial_id;
    private int movie_story_id;
    private int ad_id;
    private int ad_type;
    private String ad_pvurl;
    private String ad_linkurl;
    private String ad_makettime;
    private String ad_closetime;
    private String ad_share_cnt;
    private String ad_pvurl_vendor;
    private String content_id;
    private String content_type;
    private String content_bgcolor;
    private String share_url;
    private ShareInfoBean share_info;
    private ShareListBean share_list;
    private String orientation;
    private AnswererBean answerer;
    private String music_name;
    private String audio_platform_icon;
    private String audio_platform_name;
    private String audio_author;
    private String audio_album;
    private String cover;
    private String bg_color;
    private List<?> serial_list;
    private List<TagListBean> tag_list;
    private MenuBean menu;
    private WeatherBean weather;



    public OneFlattenBean() {

    }

    protected OneFlattenBean(Parcel in) {
        dataId = in.readString();
        date = in.readString();
        id = in.readString();
        category = in.readString();
        display_category = in.readInt();
        item_id = in.readString();
        title = in.readString();
        forward = in.readString();
        img_url = in.readString();
        like_count = in.readInt();
        post_date = in.readString();
        last_update_date = in.readString();
        video_url = in.readString();
        audio_url = in.readString();
        audio_platform = in.readInt();
        start_video = in.readString();
        has_reading = in.readInt();
        volume = in.readString();
        pic_info = in.readString();
        words_info = in.readString();
        subtitle = in.readString();
        number = in.readInt();
        serial_id = in.readInt();
        movie_story_id = in.readInt();
        ad_id = in.readInt();
        ad_type = in.readInt();
        ad_pvurl = in.readString();
        ad_linkurl = in.readString();
        ad_makettime = in.readString();
        ad_closetime = in.readString();
        ad_share_cnt = in.readString();
        ad_pvurl_vendor = in.readString();
        content_id = in.readString();
        content_type = in.readString();
        content_bgcolor = in.readString();
        share_url = in.readString();
        orientation = in.readString();
        music_name = in.readString();
        audio_platform_icon = in.readString();
        audio_platform_name = in.readString();
        audio_author = in.readString();
        audio_album = in.readString();
        cover = in.readString();
        bg_color = in.readString();
    }

    public static final Creator<OneFlattenBean> CREATOR = new Creator<OneFlattenBean>() {
        @Override
        public OneFlattenBean createFromParcel(Parcel in) {
            return new OneFlattenBean(in);
        }

        @Override
        public OneFlattenBean[] newArray(int size) {
            return new OneFlattenBean[size];
        }
    };

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDisplay_category() {
        return display_category;
    }

    public void setDisplay_category(int display_category) {
        this.display_category = display_category;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public int getAudio_platform() {
        return audio_platform;
    }

    public void setAudio_platform(int audio_platform) {
        this.audio_platform = audio_platform;
    }

    public String getStart_video() {
        return start_video;
    }

    public void setStart_video(String start_video) {
        this.start_video = start_video;
    }

    public int getHas_reading() {
        return has_reading;
    }

    public void setHas_reading(int has_reading) {
        this.has_reading = has_reading;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPic_info() {
        return pic_info;
    }

    public void setPic_info(String pic_info) {
        this.pic_info = pic_info;
    }

    public String getWords_info() {
        return words_info;
    }

    public void setWords_info(String words_info) {
        this.words_info = words_info;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(int serial_id) {
        this.serial_id = serial_id;
    }

    public int getMovie_story_id() {
        return movie_story_id;
    }

    public void setMovie_story_id(int movie_story_id) {
        this.movie_story_id = movie_story_id;
    }

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public int getAd_type() {
        return ad_type;
    }

    public void setAd_type(int ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_pvurl() {
        return ad_pvurl;
    }

    public void setAd_pvurl(String ad_pvurl) {
        this.ad_pvurl = ad_pvurl;
    }

    public String getAd_linkurl() {
        return ad_linkurl;
    }

    public void setAd_linkurl(String ad_linkurl) {
        this.ad_linkurl = ad_linkurl;
    }

    public String getAd_makettime() {
        return ad_makettime;
    }

    public void setAd_makettime(String ad_makettime) {
        this.ad_makettime = ad_makettime;
    }

    public String getAd_closetime() {
        return ad_closetime;
    }

    public void setAd_closetime(String ad_closetime) {
        this.ad_closetime = ad_closetime;
    }

    public String getAd_share_cnt() {
        return ad_share_cnt;
    }

    public void setAd_share_cnt(String ad_share_cnt) {
        this.ad_share_cnt = ad_share_cnt;
    }

    public String getAd_pvurl_vendor() {
        return ad_pvurl_vendor;
    }

    public void setAd_pvurl_vendor(String ad_pvurl_vendor) {
        this.ad_pvurl_vendor = ad_pvurl_vendor;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getContent_bgcolor() {
        return content_bgcolor;
    }

    public void setContent_bgcolor(String content_bgcolor) {
        this.content_bgcolor = content_bgcolor;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public ShareInfoBean getShare_info() {
        return share_info;
    }

    public void setShare_info(ShareInfoBean share_info) {
        this.share_info = share_info;
    }

    public ShareListBean getShare_list() {
        return share_list;
    }

    public void setShare_list(ShareListBean share_list) {
        this.share_list = share_list;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public AnswererBean getAnswerer() {
        return answerer;
    }

    public void setAnswerer(AnswererBean answerer) {
        this.answerer = answerer;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getAudio_platform_icon() {
        return audio_platform_icon;
    }

    public void setAudio_platform_icon(String audio_platform_icon) {
        this.audio_platform_icon = audio_platform_icon;
    }

    public String getAudio_platform_name() {
        return audio_platform_name;
    }

    public void setAudio_platform_name(String audio_platform_name) {
        this.audio_platform_name = audio_platform_name;
    }

    public String getAudio_author() {
        return audio_author;
    }

    public void setAudio_author(String audio_author) {
        this.audio_author = audio_author;
    }

    public String getAudio_album() {
        return audio_album;
    }

    public void setAudio_album(String audio_album) {
        this.audio_album = audio_album;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public List<?> getSerial_list() {
        return serial_list;
    }

    public void setSerial_list(List<?> serial_list) {
        this.serial_list = serial_list;
    }

    public List<TagListBean> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<TagListBean> tag_list) {
        this.tag_list = tag_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataId);
        dest.writeString(date);
        dest.writeString(id);
        dest.writeString(category);
        dest.writeInt(display_category);
        dest.writeString(item_id);
        dest.writeString(title);
        dest.writeString(forward);
        dest.writeString(img_url);
        dest.writeInt(like_count);
        dest.writeString(post_date);
        dest.writeString(last_update_date);
        dest.writeString(video_url);
        dest.writeString(audio_url);
        dest.writeInt(audio_platform);
        dest.writeString(start_video);
        dest.writeInt(has_reading);
        dest.writeString(volume);
        dest.writeString(pic_info);
        dest.writeString(words_info);
        dest.writeString(subtitle);
        dest.writeInt(number);
        dest.writeInt(serial_id);
        dest.writeInt(movie_story_id);
        dest.writeInt(ad_id);
        dest.writeInt(ad_type);
        dest.writeString(ad_pvurl);
        dest.writeString(ad_linkurl);
        dest.writeString(ad_makettime);
        dest.writeString(ad_closetime);
        dest.writeString(ad_share_cnt);
        dest.writeString(ad_pvurl_vendor);
        dest.writeString(content_id);
        dest.writeString(content_type);
        dest.writeString(content_bgcolor);
        dest.writeString(share_url);
        dest.writeString(orientation);
        dest.writeString(music_name);
        dest.writeString(audio_platform_icon);
        dest.writeString(audio_platform_name);
        dest.writeString(audio_author);
        dest.writeString(audio_album);
        dest.writeString(cover);
        dest.writeString(bg_color);
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

    public static class AuthorBean {
        /**
         * user_id : 8741452
         * user_name : 粟冰箱
         * desc : 一台以鬼故事制冷的电器精。
         * wb_name :
         * is_settled : 0
         * settled_type : 0
         * summary : 一台以鬼故事制冷的电器精。
         * fans_total : 1893
         * web_url : http://image.wufazhuce.com/FjKfi58DFi5Xrwnc7a6Ac8DDTIEI
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

    public static class ShareListBean {
        /**
         * wx : {"title":"","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=singlemessage","imgUrl":"","audio":""}
         * wx_timeline : {"title":"","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=timeline","imgUrl":"","audio":""}
         * weibo : {"title":"ONE一个 爱你，就像吃蘸盐的面包，像在夜里狂热地疾走再将嘴唇凑近水龙头，像打开没有标签的沉重包裹，焦急、愉快、小心。\u2014\u2014纳齐姆·希克梅特 下载ONE一个APP:http://weibo.com/p/100404157874","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=weibo","imgUrl":"","audio":""}
         * qq : {"title":"","desc":"","link":"http://m.wufazhuce.com/one/2092?channel=qq","imgUrl":"","audio":""}
         */

        private ShareListBean.WxBean wx;
        private ShareListBean.WxTimelineBean wx_timeline;
        private ShareListBean.WeiboBean weibo;
        private ShareListBean.QqBean qq;

        public ShareListBean.WxBean getWx() {
            return wx;
        }

        public void setWx(ShareListBean.WxBean wx) {
            this.wx = wx;
        }

        public ShareListBean.WxTimelineBean getWx_timeline() {
            return wx_timeline;
        }

        public void setWx_timeline(ShareListBean.WxTimelineBean wx_timeline) {
            this.wx_timeline = wx_timeline;
        }

        public ShareListBean.WeiboBean getWeibo() {
            return weibo;
        }

        public void setWeibo(ShareListBean.WeiboBean weibo) {
            this.weibo = weibo;
        }

        public ShareListBean.QqBean getQq() {
            return qq;
        }

        public void setQq(ShareListBean.QqBean qq) {
            this.qq = qq;
        }

        public static class WxBean {
            /**
             * title :
             * desc :
             * link : http://m.wufazhuce.com/one/2092?channel=singlemessage
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

        public static class WxTimelineBean {
            /**
             * title :
             * desc :
             * link : http://m.wufazhuce.com/one/2092?channel=timeline
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

        public static class WeiboBean {
            /**
             * title : ONE一个 爱你，就像吃蘸盐的面包，像在夜里狂热地疾走再将嘴唇凑近水龙头，像打开没有标签的沉重包裹，焦急、愉快、小心。——纳齐姆·希克梅特 下载ONE一个APP:http://weibo.com/p/100404157874
             * desc :
             * link : http://m.wufazhuce.com/one/2092?channel=weibo
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
             * title :
             * desc :
             * link : http://m.wufazhuce.com/one/2092?channel=qq
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
    }

    public static class ShareInfoBean {
        /**
         * url : http://m.wufazhuce.com/one/2092
         * image : http://image.wufazhuce.com/FkbOujesR6G41UIbq9hI4LhXCh2y
         * title : VOL.2062
         * content : 爱你，就像吃蘸盐的面包，像在夜里狂热地疾走再将嘴唇凑近水龙头，像打开没有标签的沉重包裹，焦急、愉快、小心。
         */

        private String url;
        private String image;
        private String title;
        private String content;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class AnswererBean {
        /**
         * user_id : 7468492
         * user_name : 昔央
         * desc : 关于小说家的唯一道德，就是吃下这个世界的噩梦。微信公众号：夕阳下的武士（ID ：hyydnsz ）
         * wb_name : @昔央
         * is_settled : 0
         * settled_type : 0
         * summary : 作者，编剧
         * fans_total : 8245
         * web_url : http://image.wufazhuce.com/FhJx82FwP9t8GKsEI284bhmtXPus
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

    public static class MenuBean {
        /**
         * vol : 2062
         * list : [{"content_type":"1","content_id":"3277","title":"散场","tag":{"id":"7","title":"ONE STORY"}},{"content_type":"1","content_id":"3280","title":"我们90后谈的，都是黄昏恋"},{"content_type":"1","content_id":"3281","title":"最好的时光","tag":{"id":"29","title":"苏更生专栏"}},{"content_type":"2","content_id":"639","serial_list":["627","631","632","633","634","636","639"],"title":"嫉妒 · 第六章 · 2008-2010"},{"content_type":"3","content_id":"2113","title":"怎么看待生活中的\"积极废人\"？"},{"content_type":"4","content_id":"2663","title":"我还是没有删除跟你有关的一切"},{"content_type":"5","content_id":"1436","title":"这世上，哪有什么不该喜欢的人呢？"}]
         */

        private String vol;
        private List<MenuBean.ListBean> list;

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<MenuBean.ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content_type : 1
             * content_id : 3277
             * title : 散场
             * tag : {"id":"7","title":"ONE STORY"}
             * serial_list : ["627","631","632","633","634","636","639"]
             */

            private String content_type;
            private String content_id;
            private String title;
            private MenuBean.ListBean.TagBean tag;
            private List<String> serial_list;

            public String getContent_type() {
                return content_type;
            }

            public void setContent_type(String content_type) {
                this.content_type = content_type;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public MenuBean.ListBean.TagBean getTag() {
                return tag;
            }

            public void setTag(MenuBean.ListBean.TagBean tag) {
                this.tag = tag;
            }

            public List<String> getSerial_list() {
                return serial_list;
            }

            public void setSerial_list(List<String> serial_list) {
                this.serial_list = serial_list;
            }

            public static class TagBean {
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

    public static class WeatherBean {
        /**
         * city_name : 地球
         * date : 2018-05-30
         * temperature : -275
         * humidity : 120
         * climate : 对流层
         * wind_direction : 一阵妖风
         * hurricane : 36级
         * icons : {"day":"weather_icon_unknown","night":"weather_icon_unknown_night"}
         */

        private String city_name;
        private String date;
        private String temperature;
        private String humidity;
        private String climate;
        private String wind_direction;
        private String hurricane;
        private WeatherBean.IconsBean icons;

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getClimate() {
            return climate;
        }

        public void setClimate(String climate) {
            this.climate = climate;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getHurricane() {
            return hurricane;
        }

        public void setHurricane(String hurricane) {
            this.hurricane = hurricane;
        }

        public WeatherBean.IconsBean getIcons() {
            return icons;
        }

        public void setIcons(WeatherBean.IconsBean icons) {
            this.icons = icons;
        }

        public static class IconsBean {
            /**
             * day : weather_icon_unknown
             * night : weather_icon_unknown_night
             */

            private String day;
            private String night;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }
        }
    }
    public MenuBean getMenu() {
        return menu;
    }

    public void setMenu(MenuBean menu) {
        this.menu = menu;
    }

    public WeatherBean getWeather() {
        return weather;
    }

    public void setWeather(WeatherBean weather) {
        this.weather = weather;
    }
}
