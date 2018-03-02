package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.VideoCompleteModule;
import com.lhy.mvp.module.manage.download.complete.VideoCompleteFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/16.
 * video 缓存完成 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoCompleteModule.class)
public interface VideoCompleteComponent {
    void inject(VideoCompleteFragment fragment);
}
