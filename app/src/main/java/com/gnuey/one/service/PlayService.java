package com.gnuey.one.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by gnuey on 2018/12/19/019
 */
public class PlayService extends Service {
    private Player player;
    public class PlayBinder extends Binder{
        public PlayService getService(){
            return PlayService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = Player.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        player.cancle();
        super.onDestroy();
    }

    public void play(String url){
        player.doPlayOnline(url);
    }
    public void stop(){
        player.doStopPlaying();
    }
}
