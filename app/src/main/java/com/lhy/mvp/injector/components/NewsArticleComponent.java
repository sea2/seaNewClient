package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.NewsArticleModule;
import com.lhy.mvp.module.news.article.NewsArticleActivity;

import dagger.Component;

/**
 * Created by long on 2017/2/3.
 * 新闻详情 Component
 */
@PerActivity
@Component(modules = NewsArticleModule.class)
public interface NewsArticleComponent {
    void inject(NewsArticleActivity activity);
}
