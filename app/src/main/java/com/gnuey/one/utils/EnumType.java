package com.gnuey.one.utils;


/**
 * Created by gnueyTc on 2018/5/30.
 */
public enum EnumType {
    /**
     * 阅读
     * content_type 1
     * @param READ
     *
     * 连载
     * content_type 2
     * @param SERIALIZE
     *
     * 问答
     * content_type 3
     * @param QA
     *
     * 音乐
     * content_type 4
     * @param MUSIC
     *
     * 影视
     * content_type 5
     * @param MOVIE
     *
     * 电台
     * content_type 6
     * @param RADIO
    */

    READ("阅读"),
    SERIALIZE("连载"),
    QA("问答"),
    MUSIC("音乐"),
    MOVIE("影视"),
    RADIO("电台");


    private String value;
    EnumType(String value){
        this.value= value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
