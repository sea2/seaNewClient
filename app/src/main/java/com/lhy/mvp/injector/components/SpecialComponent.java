package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.SpecialModule;
import com.lhy.mvp.module.news.special.SpecialActivity;

import dagger.Component;

/**
 * Created by long on 2016/8/26.
 * 专题 Component
 */
@PerActivity
@Component(modules = SpecialModule.class)
public interface SpecialComponent {
    void inject(SpecialActivity activity);
}
