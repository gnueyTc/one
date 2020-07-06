package com.gnuey.one.ui.base;



import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gnuey.one.service.PlayService;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gnueyTc on 2018/4/20.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected PlayService playService;
    private PlayServiceConnection playServiceConnection;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        ButterKnife.bind(this);
        bindService();
    }
    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

    }
    /**
     * 返回布局
     */
    protected abstract int getLayoutId();


    private void bindService(){
        Intent intent = new Intent();
        intent.setClass(this,PlayService.class);
        playServiceConnection = new PlayServiceConnection();
        bindService(intent,playServiceConnection,Context.BIND_AUTO_CREATE);
    }

    class PlayServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            playService = ((PlayService.PlayBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    class PlayReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
//    public abstract void playFloatIcon();
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Fragment 逐个出栈
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        if(playServiceConnection != null){
            unbindService(playServiceConnection);
        }
        super.onDestroy();

    }
}
