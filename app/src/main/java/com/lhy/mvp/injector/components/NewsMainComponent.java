package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.NewsMainModule;
import com.lhy.mvp.module.news.main.NewsMainFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/20.
 * 主页 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}
