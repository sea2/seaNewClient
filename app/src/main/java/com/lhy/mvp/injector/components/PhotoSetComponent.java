package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.PhotoSetModule;
import com.lhy.mvp.module.news.photoset.PhotoNewsActivity;

import dagger.Component;

/**
 * Created by long on 2016/8/29.
 * 图集 Component
 */
@PerActivity
@Component(modules = PhotoSetModule.class)
public interface PhotoSetComponent {
    void inject(PhotoNewsActivity activity);
}
