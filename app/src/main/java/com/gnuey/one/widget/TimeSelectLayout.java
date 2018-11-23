package com.gnuey.one.widget;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.gnuey.one.R;
import com.gnuey.one.utils.PixelTransformation;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnuey on 2018/11/18/018
 */
public class TimeSelectLayout extends RelativeLayout {
    private CustomRecyclerview mCustomRecyclerview;
    private Button mButton;
    private Dialog dialog;
    private String yearSelected = "";
    private String monthSelected = "";
    private String dateSelected = "";
    private List<String> monthSelectedList;
    private GetDateListener mGetDateListener;
    public TimeSelectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }
    private void initView(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_time_selecter_main,this,true);
        mCustomRecyclerview = findViewById(R.id.recycle_view);
        mButton = findViewById(R.id.bt_select);
        TypedArray attributes = context.obtainStyledAttributes(attrs,R.styleable.TimeSelectLayout);
        if(attributes != null){
            String buttonText = attributes.getString(R.styleable.TimeSelectLayout_button_text);
            boolean isButtonOnTop = attributes.getBoolean(R.styleable.TimeSelectLayout_button_top,true);
            boolean isManagerNormal = attributes.getBoolean(R.styleable.TimeSelectLayout_layout_manager_normal,true);
            mButton.setText(buttonText);
            if(isManagerNormal){
                mCustomRecyclerview.setLayoutManager(new LinearLayoutManager(context));
            }else {
                mCustomRecyclerview.setLayoutManager(new GridLayoutManager(context,2));
            }
            if(isButtonOnTop){
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.BELOW,R.id.bt_select);
                mCustomRecyclerview.setLayoutParams(layoutParams);
                initTimePickeer(context,Gravity.TOP);
            }else {
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                mButton.setLayoutParams(layoutParams);
                initTimePickeer(context,Gravity.BOTTOM);
            }

        }

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
    public void initTimePickeer(Context context,int gravity){

        dialog = new Dialog(context,R.style.ActionSheetDialogStyle);
        View inflater = LayoutInflater.from(context).inflate(R.layout.time_picker,null);
        LoopView loopViewYear = inflater.findViewById(R.id.lv_year);
        LoopView loopViewMonth = inflater.findViewById(R.id.lv_month);
        Button mButtom = inflater.findViewById(R.id.bt_ok);
        dialog.setContentView(inflater);

        Calendar year = Calendar.getInstance();
        year.add(Calendar.YEAR,0);
        Calendar month = Calendar.getInstance();
        month.add(Calendar.MONTH,0);

        List<String> yearList = new ArrayList<>();
        List<String> monthList = new ArrayList<>();
        List<String> monthList2012 = new ArrayList<>();
        List<String> monthListThisYear = new ArrayList<>();

        for(int i = 2012;i<year.get(Calendar.YEAR)+1;i++){//最久的数据是2012年的
            yearList.add(0,String.valueOf(i));
        }
        for(int i = 1;i<13;i++){//正常月份
            monthList.add(0,String.valueOf(i));
        }
        for(int i = 10;i<13;i++){//最久的数据2012年10月
            monthList2012.add(0,String.valueOf(i));
        }
        for(int i = 1;i<month.get(Calendar.MONTH)+2;i++){//最新年的月份，可能没到12月
            monthListThisYear.add(0,String.valueOf(i));
        }

        Window dialogWindow = dialog.getWindow();
        if(dialogWindow == null){
            return;
        }
        loopViewYear.setItems(yearList);
        loopViewMonth.setItems(monthListThisYear);
        yearSelected = yearList.get(0);
        monthSelected = monthListThisYear.get(0);//onItemSelected要滑动才触发，没滑动就默认选择了是最新月份
        loopViewYear.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(index == 0){
                    loopViewMonth.setItems(monthListThisYear);
                    monthSelectedList = monthListThisYear;
                }else if(index == yearList.size()-1){
                    loopViewMonth.setItems(monthList2012);
                    monthSelectedList = monthList2012;
                }else {
                    loopViewMonth.setItems(monthList);
                    monthSelectedList = monthList;
                }
                yearSelected = yearList.get(index);
                monthSelected = monthSelectedList.get(0);//onItemSelected要滑动才触发，没滑动就默认选择了是最新月份
            }
        });
        loopViewMonth.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                monthSelected = monthSelectedList.get(index);
            }
        });
        mButtom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dateSelected = new StringBuilder().append(yearSelected).append("-").append(monthSelected).toString();//最终返回的日期
                if(mGetDateListener!=null){
                    mGetDateListener.getDate(dateSelected);
                }
                Log.e("TimeSelectLayout", "onClick: "+dateSelected );
                dialog.dismiss();
            }
        });
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(gravity);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if(gravity==Gravity.TOP){
            lp.y = PixelTransformation.dpToPx(context,50);//设置Dialog距离底部的距离
        }
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);

    }
    public void show(boolean isShow){
        TimeSelectLayout.this.setVisibility(isShow?VISIBLE:INVISIBLE);
    }
    public void setAdapter(MultiTypeAdapter adapter){
        mCustomRecyclerview.setAdapter(adapter);
    }

    public void setGetDateListener(GetDateListener getDateListener){
        this.mGetDateListener = getDateListener;
    }
    public interface GetDateListener{
        void getDate(String date);
    }
}
