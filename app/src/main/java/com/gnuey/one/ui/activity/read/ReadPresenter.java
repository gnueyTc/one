package com.gnuey.one.ui.activity.read;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.bean.activity.read.CommentBean;
import com.gnuey.one.bean.activity.read.ReadActivityBean;
import com.gnuey.one.bean.activity.read.OneHtmlContentBean;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.ui.base.RxPresenter;
import com.gnuey.one.utils.Constant;



import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by gnuey on 2018/11/26/026
 */
public class ReadPresenter extends RxPresenter<ReadContract.View> implements ReadContract.Presenter {
    public static final String TAG = ReadPresenter.class.getSimpleName();
    private Retrofit retrofit;
    private List<OneHtmlContentBean> dataList = new ArrayList<>();
    private String lastCommentId = "0";
    @Inject
    public ReadPresenter(Retrofit retrofit){
        this.retrofit = retrofit;
    }



    @Override
    public void getCommontData(String type, String contentId) {
        addSubscribe(retrofit.create(OnePagerApi.class).getComment(type,contentId,lastCommentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(commentBean -> {
//                    Log.e("ReadPresenter", "accept: "+type);
                if(!"0".equals(lastCommentId) && commentBean.getData().getData().size() == 0){
                    mView.onShowNoMore();
                    return;
                }
                List<CommentBean.DataBeanX.DataBean> dataBeanList = commentBean.getData().getData();
                ReadPresenter.this.setCommontAdapter(dataBeanList);
                lastCommentId = dataBeanList.get(dataBeanList.size() - 1).getId();

            }, throwable -> mView.onShowNetError()));

    }

    @Override
    public void getReadData(String type, String contentId,String source,String sourceId) {
        Observable<OneHtmlContentBean> observableWeb= retrofit.create(OnePagerApi.class).getHtmlContent(type,contentId,source,sourceId)
                .subscribeOn(Schedulers.io());
        Observable<AuthorBean> observableAuthor = retrofit.create(OnePagerApi.class).getAuthor(Constant.getCategory(type),contentId)
                .subscribeOn(Schedulers.io());
        addSubscribe(Observable.zip(observableWeb, observableAuthor, (oneHtmlContentBean, authorBean) -> {
            ReadActivityBean readActivityBean = new ReadActivityBean();
            WebBean webBean = new WebBean();
            webBean.setUrl(oneHtmlContentBean.getData().getHtml_content());
            webBean.setMimeType("text/html; charset=UTF-8");
            webBean.setEncoding("utf-8");
            webBean.setId(sourceId);
            readActivityBean.setWebBean(webBean);
            if(authorBean.getData().size()>0){
            readActivityBean.setAuthorDataBean(authorBean.getData().get(0));
            }else {
                readActivityBean.setAuthorDataBean(null);
            }
//                Log.e("ReadPresenter", "apply: "+type);
            return readActivityBean;
        }).observeOn(AndroidSchedulers.mainThread())
         .subscribe(readActivityBean -> mView.doSetReadData(readActivityBean), throwable -> {
            mView.onShowNetError();
//                 Log.e("ReadPresenter", "throwable: "+throwable);
         }));
    }



    @Override
    public void setCommontAdapter(List<?> list) {
        mView.doSetCommontAdapter(list);
    }


    @Override
    public void doShowNetError() {

    }
}
