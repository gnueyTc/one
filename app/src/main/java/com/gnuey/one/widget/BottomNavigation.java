package com.gnuey.one.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.gnuey.one.R;


/**
 * Created by gnueyTc on 2017/11/1.
 */

public class BottomNavigation extends RelativeLayout {
    protected OnBottomNavigationSelectedListener mSelectionListener;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton[] radioButtonsArrays;

    public BottomNavigation(Context context) {
        super(context);
        initView(context);
    }

    public BottomNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.bottom_navigation, this);
        mRadioButton1 =  view.findViewById(R.id.rb1);
        mRadioButton2 =  view.findViewById(R.id.rb2);
        mRadioButton3 =  view.findViewById(R.id.rb3);

        radioButtonsArrays = new RadioButton[]{
                mRadioButton1,
                mRadioButton2,
                mRadioButton3,

        };
        mRadioButton1.setOnClickListener(v -> {
            mSelectionListener.onValueSelected(0);
            setBottomNavigationClick(0);
        });
        mRadioButton2.setOnClickListener(v -> {
            mSelectionListener.onValueSelected(1);
            setBottomNavigationClick(1);
        });
        mRadioButton3.setOnClickListener(v -> {
            mSelectionListener.onValueSelected(2);
            setBottomNavigationClick(2);
        });

    }
    public void setBottomNavigationSelectedListener(OnBottomNavigationSelectedListener listener) {
        this.mSelectionListener = listener;
        this.mSelectionListener.onValueSelected(0);
        setBottomNavigationClick(0);
    }

    public void setBottomNavigationClick(int index) {
        switch (index) {
            case 0:
                mRadioButton1.setChecked(true);
                break;
            case 1:
                mRadioButton2.setChecked(true);
                break;
            case 2:
                mRadioButton3.setChecked(true);
                break;

        }
    }
}
