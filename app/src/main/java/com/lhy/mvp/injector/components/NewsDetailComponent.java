package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.NewsDetailModule;
import com.lhy.mvp.module.news.detail.NewsDetailActivity;

import dagger.Component;

/**
 * Created by long on 2016/8/25.
 * 新闻详情 Component
 */
@Deprecated
@PerActivity
@Component(modules = NewsDetailModule.class)
public interface NewsDetailComponent {
    void inject(NewsDetailActivity activity);
}
