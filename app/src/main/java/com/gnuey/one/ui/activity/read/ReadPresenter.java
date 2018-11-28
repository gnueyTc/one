package com.gnuey.one.ui.activity.read;

import android.util.Log;

import com.gnuey.one.api.OnePagerApi;
import com.gnuey.one.bean.activity.read.AuthorBean;
import com.gnuey.one.bean.activity.read.CommentBean;
import com.gnuey.one.bean.activity.read.ReadActivityBean;
import com.gnuey.one.bean.activity.read.OneHtmlContentBean;
import com.gnuey.one.bean.activity.read.WebBean;
import com.gnuey.one.ui.base.RxPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by gnuey on 2018/11/26/026
 */
public class ReadPresenter extends RxPresenter<ReadContract.View> implements ReadContract.Presenter {
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
            .subscribe(new Consumer<CommentBean>() {
                @Override
                public void accept(CommentBean commentBean) throws Exception {
                    Log.e("ReadPresenter", "accept: "+commentBean.getData().getData().size());
                    if(!"0".equals(lastCommentId) && commentBean.getData().getData().size() == 0){
                        mView.onShowNoMore();
                        return;
                    }
                    List<CommentBean.DataBeanX.DataBean> dataBeanList = commentBean.getData().getData();
                    ReadPresenter.this.setCommontAdapter(dataBeanList);
                    lastCommentId = dataBeanList.get(dataBeanList.size() - 1).getId();

                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mView.onShowNetError();
                }
            }));

    }

    @Override
    public void getReadData(String type, String contentId) {
        Observable<OneHtmlContentBean> observableWeb= retrofit.create(OnePagerApi.class).getHtmlContent(type,contentId)
                .subscribeOn(Schedulers.io());
        Observable<AuthorBean> observableComment = retrofit.create(OnePagerApi.class).getAuthor(contentId)
                .subscribeOn(Schedulers.io());
        addSubscribe(Observable.zip(observableWeb, observableComment, new BiFunction<OneHtmlContentBean, AuthorBean, ReadActivityBean>() {
            @Override
            public ReadActivityBean apply(OneHtmlContentBean oneHtmlContentBean, AuthorBean authorBean) throws Exception {
                ReadActivityBean readActivityBean = new ReadActivityBean();
                WebBean webBean = new WebBean();
                webBean.setUrl(parseHtml(oneHtmlContentBean.getData().getHtml_content()));
                webBean.setMimeType("text/html");
                webBean.setEncoding("utf-8");
                readActivityBean.setWebBean(webBean);
                readActivityBean.setAuthorDataBean(authorBean.getData().get(0));
                return readActivityBean;
            }
        }).observeOn(AndroidSchedulers.mainThread())
         .subscribe(new Consumer<ReadActivityBean>() {
             @Override
             public void accept(ReadActivityBean readActivityBean) throws Exception {
                mView.doSetReadData(readActivityBean);
             }
         }, new Consumer<Throwable>() {
             @Override
             public void accept(Throwable throwable) throws Exception {
                mView.onShowNetError();
             }
         }));
    }



    @Override
    public void setCommontAdapter(List<?> list) {
        mView.doSetCommontAdapter(list);
    }

    private String parseHtml(String HTML){
        Document document = Jsoup.parse(HTML);
        document.select("div.one-authors-box").first().remove();
        document.select("div.one-copyright-box").first().remove();
        document.select("div.one-relates-box").first().remove();
        document.select("div.one-comments-box").first().remove();
        Log.e("ChannelDetailPresenter", "parseHtml: " + document.select("div.onevue-readingaudio-box"));
       return document.toString();
    }
    @Override
    public void doShowNetError() {

    }
}
