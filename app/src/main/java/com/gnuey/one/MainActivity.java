package com.gnuey.one;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gnuey.one.binder.activity.ReadActivityWebViewBinder;
import com.gnuey.one.ui.allpager.AllTabLayout;
import com.gnuey.one.ui.base.BaseActivity;
import com.gnuey.one.ui.mepager.MeTabLayout;
import com.gnuey.one.ui.onepager.OneTabLayout;
import com.gnuey.one.utils.RxBus;
import com.gnuey.one.widget.BottomNavigation;
import com.gnuey.one.widget.OnBottomNavigationSelectedListener;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements OnBottomNavigationSelectedListener {

    @BindView(R.id.bottom_navigation)
    public BottomNavigation bottomNavigation;

    @BindView(R.id.container)
    FrameLayout frameLayout;

    @BindView(R.id.iv_play)
    ImageView imageView;

    private FragmentTransaction fragmentTransaction;
    private OneTabLayout mOneTabLayout;
    private AllTabLayout mAllTabLayout;
    private MeTabLayout mMeTabLayout;
    private Fragment mCurrentFragmet;
    private AnimationDrawable animationDrawable;
    private Flowable<String> playAudioBeanObservable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigation.setBottomNavigationSelectedListener(this);
        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_audio_loading);
        playAudioBeanObservable = RxBus.getInstance().register(ReadActivityWebViewBinder.TAG,String.class);
        playAudioBeanObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String url){
                if(url.equals(ReadActivityWebViewBinder.STOP_PLAYING)){
                    playService.stop();
                    playImage(false);
                }else {
                    playService.play(url);
                    playImage(true);
                }
            }
        });
    }
    private void playImage(boolean isPlaying){
        if(isPlaying){
            imageView.setImageDrawable(animationDrawable);
            animationDrawable.start();
        }else {
            imageView.setImageResource(R.drawable.float_player_pause);
            animationDrawable.stop();
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onValueSelected(int index) {
        switch (index){
            case 0:
                if(mOneTabLayout == null){
                    mOneTabLayout = new OneTabLayout();
                    mCurrentFragmet = mOneTabLayout;
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction
                            .add(R.id.container, mOneTabLayout)
                            .commit();
                }
                switchContent(mOneTabLayout);
                break;
            case 1:
                if(mAllTabLayout == null){
                    mAllTabLayout = new AllTabLayout();
                }
                switchContent(mAllTabLayout);
                break;
            case 2:
                if(mMeTabLayout == null){
                    mMeTabLayout = new MeTabLayout();
                }
                switchContent(mMeTabLayout);
                break;
            default:
                break;

        }
    }

    private void switchContent(Fragment fragment) {
        if (mCurrentFragmet != fragment) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded()) {
                fragmentTransaction
                        .hide(mCurrentFragmet)
                        .add(R.id.container, fragment)
                        .commit();
            } else {
                fragmentTransaction
                        .hide(mCurrentFragmet)
                        .show(fragment)
                        .commit();
            }
            mCurrentFragmet = fragment;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegisterEach(ReadActivityWebViewBinder.TAG);
        if(mCurrentFragmet!=null){
            mCurrentFragmet = null;
        }
    }
}
