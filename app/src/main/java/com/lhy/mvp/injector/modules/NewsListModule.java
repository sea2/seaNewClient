package com.lhy.mvp.injector.modules;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.lhy.mvp.adapter.NewsMultiListAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.module.base.IBasePresenter;
import com.lhy.mvp.module.news.newslist.NewsListFragment;
import com.lhy.mvp.module.news.newslist.NewsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/23.
 * 新闻列表 Module
 */
@Module
public class NewsListModule {

    private final NewsListFragment mNewsListView;
    private final String mNewsId;

    public NewsListModule(NewsListFragment view, String newsId) {
        this.mNewsListView = view;
        this.mNewsId = newsId;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsListPresenter(mNewsListView, mNewsId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new NewsMultiListAdapter(mNewsListView.getContext());
    }
}
