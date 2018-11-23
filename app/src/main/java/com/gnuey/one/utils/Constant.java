package com.gnuey.one.utils;

import com.gnuey.one.InitApp;

import java.io.File;

/**
 * Created by gnueyTc on 2018/5/30.
 */
public class Constant {
    public static final String HOST = "http://v3.wufazhuce.com:8000/";
    public static final String SERVER = "api/";
    public static final String API_BASE_URL = HOST + SERVER;
    public static final String PATH_DATA = InitApp.getApplication().getCacheDir().getAbsolutePath()+ File.separator+"data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final long CACHE_SIZE = 1024 * 1024 * 50;
    public static final String TYPE_READ = "1";
    public static final String TYPE_SERIALIZE = "2";
    public static final String TYPE_QA = "3";
    public static final String TYPE_MUSIC = "4";
    public static final String TYPE_MOVIE = "5";
}
