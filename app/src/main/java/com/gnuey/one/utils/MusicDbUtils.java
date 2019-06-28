package com.gnuey.one.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gnuey.one.InitApp;
import com.gnuey.one.bean.activity.read.PlayAudioBean;
import com.gnuey.one.storage.db.greendao.DaoMaster;
import com.gnuey.one.storage.db.greendao.DaoSession;
import com.gnuey.one.storage.db.greendao.PlayAudioBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by gnuey on 2018/12/25/025
 */
public class MusicDbUtils {
    private static MusicDbUtils musicDbUtils;
    private final static String dbName = "music.db";
    private DaoMaster.DevOpenHelper helper;
    private Context context;
    private PlayAudioBeanDao playAudioBeanDao;

    private MusicDbUtils(){
        init();
    }

    public static MusicDbUtils getInstance(){
        if(musicDbUtils == null){
            synchronized (MusicDbUtils.class){
                if(musicDbUtils == null){
                    musicDbUtils = new MusicDbUtils();
                }
            }
        }
        return musicDbUtils;
    }

    private void init(){
        context = InitApp.getApplication();
        helper = new DaoMaster.DevOpenHelper(context,dbName,null);
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        playAudioBeanDao = daoSession.getPlayAudioBeanDao();
    }
    /**
     *
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase(){
        if(helper == null){
            helper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        return db;
    }

    /**
     *
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase(){
        if(helper == null){
            helper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        return db;
    }

    /**
     *
     * 增加数据
     */
    public void saveAudio(PlayAudioBean bean){
        playAudioBeanDao.insertOrReplace(bean);
    }

    /**
     *
     * 删除数据
     */
    public void deleteAudio(PlayAudioBean bean){
        playAudioBeanDao.delete(bean);
    }

    /**
     *
     * 删除所有数据
     */
    public void deleteAllAudio(){
        playAudioBeanDao.deleteAll();
    }

    /**
     *
     * 获取所有数据
     */
    public List<PlayAudioBean> queryAllAudio(){
        QueryBuilder<PlayAudioBean> queryBuilder = playAudioBeanDao.queryBuilder();
        return queryBuilder.build().list();
    }
}
