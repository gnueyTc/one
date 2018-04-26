package com.gnuey.one;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.gnuey.one.ui.base.BaseActivity;
import com.gnuey.one.ui.onepager.OneTabPage;
import com.gnuey.one.utils.BottomNavigation;
import com.gnuey.one.utils.OnBottomNavigationSelectedListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnBottomNavigationSelectedListener{

    @BindView(R.id.bottom_navigation)
    BottomNavigation bottomNavigation;
    @BindView(R.id.container)
    FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigation.setBottomNavigationSelectedListener(this);
        OneTabPage oneArticleView = new OneTabPage();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,oneArticleView,oneArticleView.getClass().getName())
        .show(oneArticleView)
        .commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onValueSelected(int index) {

    }
}
