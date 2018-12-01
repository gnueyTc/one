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

    public static final String getType(String type){
        String param = "";
        switch (type){
            case Constant.TYPE_READ:
                param =  "essay";
                break;
            case Constant.TYPE_SERIALIZE:
                param = "serialcontent";
                break;
            case Constant.TYPE_QA:
                param = "question";
                break;
            case Constant.TYPE_MUSIC:
                param = "music";
                break;
            case Constant.TYPE_MOVIE:
                param = "movie";
                break;
            default:
                break;
        }
        return param;
    }
    public static final String getCategory(String type){
        String param = "";
        switch (type){
            case "essay":
                param =  Constant.TYPE_READ;
                break;
            case "serialcontent":
                param = Constant.TYPE_SERIALIZE;
                break;
            case "question":
                param = Constant.TYPE_QA;
                break;
            case "music":
                param = Constant.TYPE_MUSIC;
                break;
            case "movie":
                param = Constant.TYPE_MOVIE;
                break;
            default:
                break;
        }
        return param;
    }

    public static final String JS_INJECT_READING = "javascript:(function(){" +
            "var obj =document.getElementById(\"oneReadingAudioTemplate\").value;" +
            "    obj.onclick=function()  " +
            "  {  "
            + "        window.listener.click();  " +
            "  }  " +
            "})()";
    public static final String JS_INJECT_DIV = "javascript:(function(){" +
            "var testElements  = document.getElementsByClass(\"onevue-readingaudio-box\"); " +
            "var testDivs = Array.prototype.filter.call(testElements, function(testElement)"+
            " { "+
            "   return testElement.nodeName === \'DIV\';" +
            "  });"+
            "for(var i=0;i<objs.length;i++)  " +
            "{"
            + "    testDivs[i].onclick=function()  " +
            "    {  "
            + "       window.listener.click();   " +
            "    }  " +
            "}" +
            "})()";

    public static final String JS_INJECT_IMG = "javascript:(function(){" +
            "var objs = document.getElementsByTagName(\"div\"); "
            + "var spans = objs[6].getElementsByTagName(\"span\");"
            + "    objs[3].onclick=function()  " +
            "    {  "
            + "        window.listener.click(spans[2].innerText); " +
            "    }  " +
            "})()";
}
