package com.gnuey.one;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.binder.onepager.OneArticleViewBinder;
import com.gnuey.one.binder.onepager.OneArticleViewHeadBinder;
import com.gnuey.one.binder.onepager.OneArticleViewMenuBinder;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnueyTc on 2018/5/2.
 */
public class Register {
    public static final String TAG = "Register";
    public static void registerOneArticleItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(OneListBean.DataBean.ContentListBean.class)
                .to(new OneArticleViewHeadBinder(),
                        new OneArticleViewBinder())
                .withClassLinker(new ClassLinker<OneListBean.DataBean.ContentListBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<OneListBean.DataBean.ContentListBean, ?>> index(int position, @NonNull OneListBean.DataBean.ContentListBean contentListBean) {
                        Log.e(TAG, "index: position = "+ position );
                        if(contentListBean.getCategory().equals("0")){
                            return OneArticleViewHeadBinder.class;
                        }else {
                            return OneArticleViewBinder.class;
                        }
                    }
                });
        adapter.register(OneListBean.DataBean.MenuBean.class,new OneArticleViewMenuBinder());

    }
}
