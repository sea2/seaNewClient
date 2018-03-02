package com.lhy.mvp.module.news.detail;

import com.lhy.mvp.api.bean.NewsDetailInfo;
import com.lhy.mvp.module.base.IBaseView;
import com.lhy.mvp.api.bean.NewsDetailInfo;

/**
 * Created by long on 2016/8/25.
 * 新闻详情接口
 */
@Deprecated
public interface INewsDetailView extends IBaseView {

    /**
     * 显示数据
     * @param newsDetailBean 新闻详情
     */
    void loadData(NewsDetailInfo newsDetailBean);
}
