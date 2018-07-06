package com.gnuey.one;

import android.support.annotation.NonNull;

import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.bean.onepager.OneListBean;
import com.gnuey.one.binder.onepager.OneArticleViewBinder;
import com.gnuey.one.binder.onepager.OneArticleViewHeadBinder;
import com.gnuey.one.binder.onepager.OneArticleViewMenuBinder;
import com.gnuey.one.binder.onepager.OneArticleViewMenuItemBinder;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnueyTc on 2018/5/2.
 */
public class Register {
    public static final String TAG = "Register";
    public static void registerOneArticleItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(OneFlattenBean.class)
                .to(new OneArticleViewHeadBinder(),
                        new OneArticleViewBinder(),
                        new OneArticleViewMenuBinder())
                .withClassLinker((position, oneFlattenBean) -> {
                    if(oneFlattenBean.getContent_type().equals("0")){
                        return OneArticleViewHeadBinder.class;
                    }else if(oneFlattenBean.getContent_type().equals("-1")){
                        return OneArticleViewMenuBinder.class;
                    }else{
                        return OneArticleViewBinder.class;
                    }

                });

    }
    public static void registerOneArticleMenuItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(OneFlattenBean.MenuBean.ListBean.class,new OneArticleViewMenuItemBinder());
    }
}
