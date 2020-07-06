package com.gnuey.one.ui.base;



import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.gnuey.one.InitApp;
import com.gnuey.one.component.AppComponent;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements IBaseView {
    private static final String TAG = BaseFragment.class.getSimpleName();

    protected Context mContext;
    protected ViewGroup parent;
    private View rootView;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutId();

    /**
     * 初始化视图控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData() throws NullPointerException;

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, String title) {
        ((BaseActivity) getActivity()).initToolBar(toolbar, title);
    }
    /**
     * 设置appcomponent
     */
    protected void setAppComponent(AppComponent appComponent){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(attachLayoutId(),container,false);
        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        if(viewGroup != null){
            viewGroup.removeView(rootView);
        }
        setAppComponent(InitApp.getApplication().getAppComponent());
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initView(view);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(this.mContext != null){
            this.mContext = null;
        }
        Log.e(TAG, "onDetach: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        InitApp.getRefWatcher().watch(this);
    }
}
