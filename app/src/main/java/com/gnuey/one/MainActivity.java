package com.gnuey.one;

import android.os.Bundle;

import com.gnuey.one.module.base.BaseActivity;
import com.gnuey.one.utils.BottomNavigation;
import com.gnuey.one.utils.OnBottomNavigationSelectedListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnBottomNavigationSelectedListener{

    @BindView(R.id.bottom_navigation)
    BottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigation.setBottomNavigationSelectedListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onValueSelected(int index) {

    }
}
