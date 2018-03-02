package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.PhotoNewsModule;
import com.lhy.mvp.module.photo.news.PhotoNewsFragment;

import dagger.Component;

/**
 * Created by long on 2016/9/5.
 * 图片新闻列表 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = PhotoNewsModule.class)
public interface PhotoNewsComponent {
    void inject(PhotoNewsFragment fragment);
}
