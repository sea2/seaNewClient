package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.VideoCacheModule;
import com.lhy.mvp.module.manage.download.cache.VideoCacheFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/16.
 * video缓存Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoCacheModule.class)
public interface VideoCacheComponent {
    void inject(VideoCacheFragment fragment);
}
