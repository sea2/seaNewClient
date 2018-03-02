package com.lhy.mvp.module.news.article;

import com.lhy.mvp.api.bean.NewsDetailInfo;
import com.lhy.mvp.module.base.IBaseView;
import com.lhy.mvp.api.bean.NewsDetailInfo;

/**
 * Created by long on 2017/2/3.
 * 新闻详情接口
 */
public interface INewsArticleView extends IBaseView {

    /**
     * 显示数据
     * @param newsDetailBean 新闻详情
     */
    void loadData(NewsDetailInfo newsDetailBean);
}


