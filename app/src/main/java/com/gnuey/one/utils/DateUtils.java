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
    private SimpleDateFormat format1;
    private SimpleDateFormat format2;
    private SimpleDateFormat format3;
    private SimpleDateFormat format4;
    public final static int FORMAT_DDMMYYYY = 3;
    public final static int FORMAT_YYYYMMDD = 4;
    private int dayApart = 0;
    private boolean isDayApartAlreadyCalculated = false;
    private String todayDate = "";
    private String ortherDate = "";
    public String currentDateForMat3 = "";//当前页面时间
    public String currentDateForMat4 = "";//当前页面时间
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

    /**
     * 获取当前月日
     *
     * @param count 当前日期与最新数据日期是否有差距
     * @return 30 May.2018
     */
    public String getDate(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0 - count);
        this.currentDateForMat3 = format3.format(calendar.getTime());
        this.currentDateForMat4 = format4.format(calendar.getTime());
        return currentDateForMat3;

    }

    /**
     * 获取当前月日
     *
     * @param count 当前日期与最新数据日期是否有差距
     * @return 2018-05-30
     */
    public String getSearchDate(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0 - count);
        this.currentDateForMat4 = format4.format(calendar.getTime());
        return currentDateForMat4;
    }

    /**
     * 计算当前日期最新数据是否有差距，相差1天为1如此类推
     *
     * @param date 服务器获取的日期 2018-05-30 06:00:00
     */
    public void calculaDayApart(String date) {
        if (isDayApartAlreadyCalculated) {
            return;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            Date d0 = format4.parse(format4.format(calendar.getTime()));
            Date d1 = format4.parse(date);
            dayApart = (int) ((d0.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000));
            RxBus.getInstance().post(TAG,dayApart);
            isDayApartAlreadyCalculated = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "calculaDayApart: dayApart = " + dayApart);
    }

}
