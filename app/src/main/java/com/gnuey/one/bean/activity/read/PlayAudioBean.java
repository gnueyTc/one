package com.gnuey.one.bean.activity.read;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by gnuey on 2018/12/20/020
 */
@Entity(nameInDb = "SystemMessage")
public class PlayAudioBean implements Serializable {

    private static final long serialVersionUID = -3302766649892882424L;
    /**
     * id : 2866
     * title : The Big Questions (with Dina Hauge)
     * author : Ulrik Haug、Dina Hauge
     * audio_url : http://music.wufazhuce.com/ljE5-O15LgPwejbXfRLe5TLLKOlN
     * audio_platform : 4
     * platform_name : 看见音乐
     * platform_icon : http://image.wufazhuce.com/FpvA4Zg2Lt6L2EuMknvwwdCC_vk0
     */
    @Id(autoincrement = true)
    @Unique
    private Long mainKey;
    @NotNull
    @Property(nameInDb = "id")
    private String id;
    @Property(nameInDb = "title")
    private String title;
    @Property(nameInDb = "author")
    private String author;
    @Property(nameInDb = "audio_url")
    private String audio_url;
    @Property(nameInDb = "audio_platform")
    private String audio_platform;
    @Property(nameInDb = "platform_name")
    private String platform_name;
    @Property(nameInDb = "platform_icon")
    private String platform_icon;

    @Generated(hash = 1874402733)
    public PlayAudioBean(Long mainKey, @NotNull String id, String title,
            String author, String audio_url, String audio_platform,
            String platform_name, String platform_icon) {
        this.mainKey = mainKey;
        this.id = id;
        this.title = title;
        this.author = author;
        this.audio_url = audio_url;
        this.audio_platform = audio_platform;
        this.platform_name = platform_name;
        this.platform_icon = platform_icon;
    }

    @Generated(hash = 1693663178)
    public PlayAudioBean() {
    }

    public Long getMainKey() {
        return mainKey;
    }

    public void setMainKey(Long mainKey) {
        this.mainKey = mainKey;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getAudio_platform() {
        return audio_platform;
    }

    public void setAudio_platform(String audio_platform) {
        this.audio_platform = audio_platform;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getPlatform_icon() {
        return platform_icon;
    }

    public void setPlatform_icon(String platform_icon) {
        this.platform_icon = platform_icon;
    }
}
