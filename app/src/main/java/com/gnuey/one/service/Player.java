package com.gnuey.one.service;

import android.media.MediaPlayer;
import android.util.Log;

import com.gnuey.one.utils.ToastUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gnuey on 2018/12/19/019
 */
public class Player {
    private final static int STATE_IDEL = 0;
    private final static int STATE_PREPARING = 1;
    private final static int STATE_PLAYING = 2;
    private final static int STATE_PAUSE = 3;
    private int state = 0;
    private static Player instance;
    private MediaPlayer mediaPlayer;
    private Disposable progressDisposable;
    private Observer<Long> observer;
    public static Player getInstance(){
        if(instance == null){
            instance = new Player();
        }
        return instance;
    }
    public Player(){
        init();
    }
    private void init(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                state = STATE_PLAYING;
                getProgressPosition();
            }
        });
    }
    private void getProgressPosition(){
        observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                progressDisposable = d;
            }

            @Override
            public void onNext(Long aLong) {
                Log.e("Player", "accept: "+ mediaPlayer.getCurrentPosition());
            }

            @Override
            public void onError(Throwable e) {
                cancle();
            }

            @Override
            public void onComplete() {
                cancle();
            }
        };
        Observable.interval(300,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
//    public void cancle(){
//        if(observer!=null){
//            observer.onComplete();
//        }
//    }
    public void cancle(){
        if(progressDisposable!=null&&!progressDisposable.isDisposed()){
            progressDisposable.dispose();
        }
    }
    public void doPlayOnline(String url){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.showSingleToast("当前歌曲无法播放");
        }
    }
    public void seekTo(int msec){
        if(isPlaying() || isPausing()){
            mediaPlayer.seekTo(msec);
        }
    }
    public void doPause(){
        if(!isPlaying()){
            return;
        }
        mediaPlayer.pause();
        state = STATE_PAUSE;
        cancle();
    }
    public void doStopPlaying(){
        if(!isPlaying()||isIdel()){
            return;
        }
        mediaPlayer.pause();
        mediaPlayer.reset();
        state = STATE_IDEL;
        cancle();
    }
    public boolean isIdel(){
        return state == STATE_IDEL;
    }
    public boolean isPlaying(){
        return state == STATE_PLAYING;
    }
    public boolean isPausing(){
        return state == STATE_PAUSE;
    }

}
