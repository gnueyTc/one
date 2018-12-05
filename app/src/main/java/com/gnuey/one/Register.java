package com.gnuey.one;

import android.support.annotation.NonNull;


import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.bean.activity.read.CommentBean;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.bean.allpage.AllListBean;
import com.gnuey.one.bean.onepage.FeedsListBean;
import com.gnuey.one.bean.onepage.OneFlattenBean;
import com.gnuey.one.binder.activity.ReadActivityAuthorBinder;
import com.gnuey.one.binder.activity.ReadActivityCommentBinder;
import com.gnuey.one.binder.activity.ReadActivityWebViewBinder;
import com.gnuey.one.binder.allpage.AllBannerBinder;
import com.gnuey.one.binder.allpage.AllTabLayoutBinder;
import com.gnuey.one.binder.onepage.OneArticleViewBinder;
import com.gnuey.one.binder.onepage.OneArticleViewHeadBinder;
import com.gnuey.one.binder.onepage.OneArticleViewMenuBinder;
import com.gnuey.one.binder.onepage.OneArticleViewMenuItemBinder;
import com.gnuey.one.binder.onepage.OneTabLayoutBinder;


import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by gnueyTc on 2018/5/2.
 */
public class Register {
    public static final String TAG = "Register";

    public static void registerOneTabLayoutItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(FeedsListBean.DataBean.class,new OneTabLayoutBinder());
    }
    public static void registerOneArticleItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(OneFlattenBean.class)
                .to(new OneArticleViewHeadBinder(),
                        new OneArticleViewBinder(),
                        new OneArticleViewMenuBinder())
                .withClassLinker(new ClassLinker<OneFlattenBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<OneFlattenBean, ?>> index(int position, @NonNull OneFlattenBean oneFlattenBean) {
                        if ("0".equals(oneFlattenBean.getContent_type())) {
                            return OneArticleViewHeadBinder.class;
                        } else if ("-1".equals(oneFlattenBean.getContent_type())) {
                            return OneArticleViewMenuBinder.class;
                        } else {
                            return OneArticleViewBinder.class;
                        }

                    }
                });

    }
    public static void registerOneArticleMenuItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(OneFlattenBean.MenuBean.ListBean.class,new OneArticleViewMenuItemBinder());
    }

    public static void registerReadActivityItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(WebBean.class,new ReadActivityWebViewBinder());
        adapter.register(AuthorBean.DataBean.class,new ReadActivityAuthorBinder());
        adapter.register(CommentBean.DataBeanX.DataBean.class,new ReadActivityCommentBinder());
    }

    public static void registerAllTabLayoutItem(@NonNull MultiTypeAdapter adapter){
        adapter.register(AllListBean.class,new AllBannerBinder());
        adapter.register(AllListBean.DataBean.class,new AllTabLayoutBinder());
    }
}
