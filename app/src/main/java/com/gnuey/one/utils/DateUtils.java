package com.gnuey.one.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gnueyTc on 2018/5/30.
 */
public class DateUtils {
    /**
     *
     * 获取当前月日
    */
    public static String getNow(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
        String now = sdf.format(d);
        return now;
    }
    public static String getNow(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
        String now = sdf.format(date);
        return now;
    }
    /**
     *
     * 获取当前月日
     *
     * @param date 服务器获取的日期 2018-05-30 06:00:00
     * @return 5月30日
     */
    public static String getTodayDate(String date){
        String todayDate="";
        String ortherDate="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf.parse(date);
            todayDate = getNow();
            ortherDate = getNow(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayDate.equals(ortherDate)?"今日":ortherDate;
    }

    public static String getDate(int count){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM.yyyy", Locale.US);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,count);
        Log.e("DateUtils", "getDate: "+sdf.format(calendar.getTime()));
        return sdf.format(calendar.getTime());
    }
}
