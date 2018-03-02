package com.lhy.mvp.module.news.newslist;

import com.lhy.mvp.api.bean.NewsInfo;
import com.lhy.mvp.adapter.item.NewsMultiItem;
import com.lhy.mvp.module.base.ILoadDataView;

import java.util.List;

/**
 * Created by long on 2016/8/23.
 * 新闻列表视图接口
 */
public interface INewsListView extends ILoadDataView<List<NewsMultiItem>> {

    /**
     * 加载广告数据
     * @param newsBean 新闻
     */
    void loadAdData(NewsInfo newsBean);
}
