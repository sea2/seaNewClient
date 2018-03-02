package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.LoveVideoModule;
import com.lhy.mvp.module.manage.love.video.LoveVideoFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/13.
 * Video收藏 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = LoveVideoModule.class)
public interface LoveVideoComponent {
    void inject(LoveVideoFragment fragment);
}
