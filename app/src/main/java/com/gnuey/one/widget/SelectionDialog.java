package com.gnuey.one.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gnuey.one.R;

/**
 * Created by gnuey on 2018/11/7/007
 */
public class SelectionDialog extends Dialog {
    public SelectionDialog(@NonNull Context context,int padding) {
        super(context,R.style.selection_dialog);
        setOwnerActivity((Activity) context);
        setContentView(R.layout.fragment_me);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = 500;


        getWindow().getDecorView().setPadding(0,0,0,0);
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void show() {
        super.show();

        Log.e("SelectionDialog", "show: " );
    }

    @Override
    public void hide() {
        super.hide();
    }


}

