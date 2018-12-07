package com.gnuey.one.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;

/**
 * Created by gnueyTc on 2018/5/30.
 */
public class DateUtils {
    public static final String TAG = DateUtils.class.getSimpleName();
    private static SimpleDateFormat format1;
    private static SimpleDateFormat format2;
    private static SimpleDateFormat format3;
    private static SimpleDateFormat format4;
    private static int dayApart = -1;
    private static String todayDate = "";
    private static String ortherDate = "";
    public static String currentDateForMat3 = "";//当前页面时间
    public static String currentDateForMat4 = "";//当前页面时间

    /**
     * 获取当前月日
     */
    public static String getNow() {
        if(format1 == null){
            format1 = new SimpleDateFormat("M月dd日");;
        }
        Date d = new Date();
        return format1.format(d);
    }

    public static String getNow(Date date) {
        if(format1 == null){
            format1 = new SimpleDateFormat("M月dd日");;
        }
        return format1.format(date);
    }

    /**
     * 获取当前月日
     *
     * @param date 服务器获取的日期 2018-05-30 06:00:00
     * @return 5月30日
     */
    public static String getTodayDate(String date) {
        if(todayDate == null){
            todayDate = getNow();
        }
        if(format2 == null){
            format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            Date d = format2.parse(date);
            ortherDate = getNow(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayDate.equals(ortherDate) ? "今日" : ortherDate;
    }

    /**
     * 获取当前月日
     *
     * @param count 当前日期与最新数据日期是否有差距
     * @return 30 May.2018
     */
    public static String getDate(int count) {
        Log.e(TAG, "getDate: " );
        if(format3 == null){
            format3 = new SimpleDateFormat("dd MMM.yyyy", Locale.US);
        }
        if(format4 == null){
            format4 = new SimpleDateFormat("yyyy-MM-dd");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0 - count);
        currentDateForMat3 = format3.format(calendar.getTime());
        currentDateForMat4 = format4.format(calendar.getTime());
        return currentDateForMat3;

    }
    public static String getDate(){
        return currentDateForMat4;
    }
    public static String getMonth(int count){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,count);
        Log.e(TAG, "getMonth: "+ format4.format(calendar.getTime()));
        return format4.format(calendar.getTime());
    }
    /**
     * 获取当前月日
     *
     * @param count 当前日期与最新数据日期是否有差距
     * @return 2018-05-30
     */
    public static String getSearchDate(int count) {
        if(format4 == null){
            format4 = new SimpleDateFormat("yyyy-MM-dd");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0 - count);
        currentDateForMat4 = format4.format(calendar.getTime());
        return currentDateForMat4;
    }

    /**
     * 计算当前日期最新数据是否有差距，相差1天为1如此类推
     *
     * @param date 服务器获取的日期 2018-05-30 06:00:00
     */
    public static void calculaDayApart(String date) {
        if(format4 == null){
            format4 = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            Calendar calendar = Calendar.getInstance();
            Date d0 = format4.parse(format4.format(calendar.getTime()));
            Date d1 = format4.parse(date);
            dayApart = (int) ((d0.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000));
            RxBus.getInstance().post(TAG,dayApart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "calculaDayApart: dayApart = " + dayApart);
    }

}
