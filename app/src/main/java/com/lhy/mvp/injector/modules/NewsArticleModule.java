package com.lhy.mvp.injector.modules;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.module.base.IBasePresenter;
import com.lhy.mvp.module.news.article.NewsArticleActivity;
import com.lhy.mvp.module.news.article.NewsArticlePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2017/2/3.
 * 新闻详情 Module
 */
@Module
public class NewsArticleModule {

    private final String mNewsId;
    private final NewsArticleActivity mView;

    public NewsArticleModule(NewsArticleActivity view, String newsId) {
        mNewsId = newsId;
        mView = view;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsArticlePresenter(mNewsId, mView);
    }

}
