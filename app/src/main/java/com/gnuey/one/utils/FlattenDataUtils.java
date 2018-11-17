package com.gnuey.one.utils;

import android.os.Parcel;

import com.gnuey.one.bean.onepager.OneFlattenBean;
import com.gnuey.one.bean.onepager.OneListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gnueyTc on 2018/5/30.
 */
public class FlattenDataUtils {
    private static ArrayList<OneFlattenBean> oneDataBeanArrayList;

    public static ArrayList<OneFlattenBean> FlattenOneListBeanList(OneListBean data) throws NullPointerException {
        oneDataBeanArrayList = new ArrayList<>();
        Parcel parcel = Parcel.obtain();
        for (OneListBean.DataBean.ContentListBean contentListBean : data.getData().getContent_list()) {
            OneFlattenBean oneFlattenBean = new OneFlattenBean();
            //
            List<OneFlattenBean.MenuBean.ListBean> menuList = new ArrayList<>();
            OneFlattenBean.MenuBean menu = new OneFlattenBean.MenuBean();
            menu.setVol(data.getData().getMenu().getVol());
            if (data.getData().getMenu().getList() != null) {
                for (OneListBean.DataBean.MenuBean.ListBean listBean : data.getData().getMenu().getList()) {
                    OneFlattenBean.MenuBean.ListBean newlListBean = new OneFlattenBean.MenuBean.ListBean();
                    newlListBean.setContent_type(listBean.getContent_type());
                    newlListBean.setContent_id(listBean.getContent_id());
                    newlListBean.setTitle(listBean.getTitle());
                    if (listBean.getTag() != null) {
                        OneFlattenBean.MenuBean.ListBean.TagBean tagBean = new OneFlattenBean.MenuBean.ListBean.TagBean();
                        tagBean.setId(listBean.getTag().getId());
                        tagBean.setTitle(listBean.getTag().getTitle());
                        newlListBean.setTag(tagBean);
                    }
                    newlListBean.setSerial_list(listBean.getSerial_list());
                    menuList.add(newlListBean);
                }
            }
            menu.setList(menuList);
            oneFlattenBean.setMenu(menu);


            oneFlattenBean.setDataId(data.getData().getId());
            oneFlattenBean.setDate(data.getData().getDate());
            oneFlattenBean.setId(contentListBean.getId());
            oneFlattenBean.setCategory(contentListBean.getCategory());
            oneFlattenBean.setDisplay_category(contentListBean.getDisplay_category());
            oneFlattenBean.setItem_id(contentListBean.getItem_id());
            oneFlattenBean.setTitle(contentListBean.getTitle());
            oneFlattenBean.setForward(contentListBean.getForward());
            oneFlattenBean.setImg_url(contentListBean.getImg_url());
            oneFlattenBean.setLike_count(contentListBean.getLike_count());
            oneFlattenBean.setPost_date(contentListBean.getPost_date());
            oneFlattenBean.setLast_update_date(contentListBean.getLast_update_date());

            //
            OneFlattenBean.AuthorBean author = new OneFlattenBean.AuthorBean();
            author.setUser_id(contentListBean.getAuthor().getUser_id());
            author.setUser_name(contentListBean.getAuthor().getUser_name());
            author.setDesc(contentListBean.getAuthor().getDesc());
            author.setWb_name(contentListBean.getAuthor().getWb_name());
            author.setIs_settled(contentListBean.getAuthor().getIs_settled());
            author.setSettled_type(contentListBean.getAuthor().getSettled_type());
            author.setSummary(contentListBean.getAuthor().getSummary());
            author.setFans_total(contentListBean.getAuthor().getFans_total());
            author.setWeb_url(contentListBean.getAuthor().getWeb_url());
            oneFlattenBean.setAuthor(author);

            //
            oneFlattenBean.setVideo_url(contentListBean.getVideo_url());
            oneFlattenBean.setAudio_url(contentListBean.getAudio_url());
            oneFlattenBean.setAudio_platform(contentListBean.getAudio_platform());
            oneFlattenBean.setStart_video(contentListBean.getStart_video());
            oneFlattenBean.setHas_reading(contentListBean.getHas_reading());
            oneFlattenBean.setVolume(contentListBean.getVolume());
            oneFlattenBean.setPic_info(contentListBean.getPic_info());
            oneFlattenBean.setWords_info(contentListBean.getWords_info());
            oneFlattenBean.setSubtitle(contentListBean.getSubtitle());
            oneFlattenBean.setNumber(contentListBean.getNumber());
            oneFlattenBean.setSerial_id(contentListBean.getSerial_id());
            oneFlattenBean.setMovie_story_id(contentListBean.getMovie_story_id());
            oneFlattenBean.setAd_id(contentListBean.getAd_id());
            oneFlattenBean.setAd_type(contentListBean.getAd_type());
            oneFlattenBean.setAd_pvurl(contentListBean.getAd_pvurl());
            oneFlattenBean.setAd_linkurl(contentListBean.getAd_linkurl());
            oneFlattenBean.setAd_makettime(contentListBean.getAd_makettime());
            oneFlattenBean.setAd_closetime(contentListBean.getAd_closetime());
            oneFlattenBean.setAd_share_cnt(contentListBean.getAd_share_cnt());
            oneFlattenBean.setAd_pvurl_vendor(contentListBean.getAd_pvurl_vendor());
            oneFlattenBean.setContent_id(contentListBean.getContent_id());
            oneFlattenBean.setContent_type(contentListBean.getContent_type());
            oneFlattenBean.setContent_bgcolor(contentListBean.getContent_bgcolor());
            oneFlattenBean.setShare_url(contentListBean.getShare_url());

            //
            OneFlattenBean.ShareInfoBean shareInfo = new OneFlattenBean.ShareInfoBean();
            if(contentListBean.getShare_info()!=null){
                shareInfo.setUrl(contentListBean.getShare_info().getUrl());
                shareInfo.setImage(contentListBean.getShare_info().getImage());
                shareInfo.setContent(contentListBean.getShare_info().getContent());
                shareInfo.setTitle(contentListBean.getShare_info().getTitle());
                oneFlattenBean.setShare_info(shareInfo);
            }


            //
            OneFlattenBean.ShareListBean shareList = new OneFlattenBean.ShareListBean();
            OneFlattenBean.ShareListBean.WxBean wx = new OneFlattenBean.ShareListBean.WxBean();
            wx.setTitle(contentListBean.getShare_list().getWx().getTitle());
            wx.setDesc(contentListBean.getShare_list().getWx().getDesc());
            wx.setLink(contentListBean.getShare_list().getWx().getLink());
            wx.setImgUrl(contentListBean.getShare_list().getWx().getImgUrl());
            wx.setAudio(contentListBean.getShare_list().getWx().getAudio());
            shareList.setWx(wx);

            OneFlattenBean.ShareListBean.WxTimelineBean wxTimeline = new OneFlattenBean.ShareListBean.WxTimelineBean();
            wxTimeline.setTitle(contentListBean.getShare_list().getWx_timeline().getTitle());
            wxTimeline.setDesc(contentListBean.getShare_list().getWx_timeline().getDesc());
            wxTimeline.setLink(contentListBean.getShare_list().getWx_timeline().getLink());
            wxTimeline.setImgUrl(contentListBean.getShare_list().getWx_timeline().getImgUrl());
            wxTimeline.setAudio(contentListBean.getShare_list().getWx_timeline().getAudio());
            shareList.setWx_timeline(wxTimeline);

            OneFlattenBean.ShareListBean.WeiboBean weibo = new OneFlattenBean.ShareListBean.WeiboBean();
            weibo.setTitle(contentListBean.getShare_list().getWeibo().getTitle());
            weibo.setDesc(contentListBean.getShare_list().getWeibo().getDesc());
            weibo.setLink(contentListBean.getShare_list().getWeibo().getLink());
            weibo.setImgUrl(contentListBean.getShare_list().getWeibo().getImgUrl());
            weibo.setAudio(contentListBean.getShare_list().getWeibo().getAudio());
            shareList.setWeibo(weibo);

            OneFlattenBean.ShareListBean.QqBean qq = new OneFlattenBean.ShareListBean.QqBean();
            qq.setTitle(contentListBean.getShare_list().getQq().getTitle());
            qq.setDesc(contentListBean.getShare_list().getQq().getDesc());
            qq.setLink(contentListBean.getShare_list().getQq().getLink());
            qq.setImgUrl(contentListBean.getShare_list().getQq().getImgUrl());
            qq.setAudio(contentListBean.getShare_list().getQq().getAudio());
            shareList.setQq(qq);

            oneFlattenBean.setShare_list(shareList);

            //
            oneFlattenBean.setOrientation(contentListBean.getOrientation());

            //
            if (contentListBean.getAnswerer() != null) {
                OneFlattenBean.AnswererBean answerer = new OneFlattenBean.AnswererBean();
                answerer.setUser_id(contentListBean.getAnswerer().getUser_id());
                answerer.setUser_name(contentListBean.getAnswerer().getUser_name());
                answerer.setDesc(contentListBean.getAnswerer().getDesc());
                answerer.setWb_name(contentListBean.getAnswerer().getWb_name());
                answerer.setIs_settled(contentListBean.getAnswerer().getIs_settled());
                answerer.setSettled_type(contentListBean.getAnswerer().getSettled_type());
                answerer.setSummary(contentListBean.getAnswerer().getSummary());
                answerer.setFans_total(contentListBean.getAnswerer().getFans_total());
                answerer.setWeb_url(contentListBean.getAnswerer().getWeb_url());

                oneFlattenBean.setAnswerer(answerer);
            }


            //
            oneFlattenBean.setMusic_name(contentListBean.getMusic_name());
            oneFlattenBean.setAudio_platform_icon(contentListBean.getAudio_platform_icon());
            oneFlattenBean.setAudio_platform_name(contentListBean.getAudio_platform_name());
            oneFlattenBean.setAudio_author(contentListBean.getAudio_author());
            oneFlattenBean.setAudio_album(contentListBean.getAudio_album());
            oneFlattenBean.setCover(contentListBean.getCover());
            oneFlattenBean.setBg_color(contentListBean.getBg_color());

            //
            oneFlattenBean.setSerial_list(contentListBean.getSerial_list());

            //
            List<OneFlattenBean.TagListBean> tagListBeanList = new ArrayList<>();
            for (OneListBean.DataBean.ContentListBean.TagListBean tagList : contentListBean.getTag_list()) {
                OneFlattenBean.TagListBean tagListBean = new OneFlattenBean.TagListBean();
                tagListBean.setId(tagList.getId());
                tagListBean.setTitle(tagList.getTitle());
                tagListBeanList.add(tagListBean);
            }
            oneFlattenBean.setTag_list(tagListBeanList);


            oneDataBeanArrayList.add(oneFlattenBean);

        }
        OneFlattenBean flattenBean = new OneFlattenBean();
        flattenBean.setMenu(oneDataBeanArrayList.get(1).getMenu());
        flattenBean.setContent_type("-1");
        oneDataBeanArrayList.add(1, flattenBean);
        return oneDataBeanArrayList;
    }
}
