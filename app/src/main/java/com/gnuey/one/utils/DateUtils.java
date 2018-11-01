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
    private static final String TAG = DateUtils.class.getSimpleName();
    private SimpleDateFormat format1;
    private SimpleDateFormat format2;
    private SimpleDateFormat format3;
    private SimpleDateFormat format4;
    private int dayApart = 0;
    private boolean isDayApartAlreadyCalculated = false;
    private String todayDate = "";
    private String ortherDate = "";
    private String searchDate = "";
    private String upToDate = "";

    public DateUtils() {
        format1 = new SimpleDateFormat("M月dd日");
        format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format3 = new SimpleDateFormat("dd MMM.yyyy", Locale.US);
        format4 = new SimpleDateFormat("yyyy-MM-dd");
        todayDate = getNow();

    }

    /**
     * 获取当前月日
     */
    public String getNow() {
        Date d = new Date();
        return format1.format(d);
    }

    public String getNow(Date date) {
        return format1.format(date);
    }

    /**
     * 获取当前月日
     *
     * @param date 服务器获取的日期 2018-05-30 06:00:00
     * @return 5月30日
     */
    public String getTodayDate(String date) {
        try {
            Date d = format2.parse(date);
            ortherDate = getNow(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayDate.equals(ortherDate) ? "今日" : ortherDate;
    }

    public String getDate(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0 - count);
        return format3.format(calendar.getTime());
    }

    public String getSearchDate(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0 - count);
        Log.e(TAG, "getSearchDate: " + format4.format(calendar.getTime()));
        return format4.format(calendar.getTime());
    }

    public String getUpToDate() {
        return "".equals(getUpToDate()) ? "0" : upToDate;
    }

    public String getDate(String date) {
        Log.e(TAG, "getDate: dayApart = " + dayApart);
        if (!"".equals(date)) {
            return date;
        }
        if (isDayApartAlreadyCalculated) {
            dayApart += 1;
            return getSearchDate(dayApart);
        }else {
            return "0";
        }
    }


    public void calculaDayApart(String date) {
        if (isDayApartAlreadyCalculated) {
            return;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            Date d0 = format4.parse(format4.format(calendar.getTime()));
            Date d1 = format4.parse(date);
            dayApart = (int) ((d0.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000));
            RxBus.getInstance().post(dayApart);
            isDayApartAlreadyCalculated = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "calculaDayApart: dayApart = " + dayApart);
    }
//    public static String getDate(int count){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH,count);
//        return sdf.format(calendar.getTime());
//    }
}
