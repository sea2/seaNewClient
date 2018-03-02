package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.VideoListModule;
import com.lhy.mvp.module.video.list.VideoListFragment;

import dagger.Component;

/**
 * Created by long on 2016/10/11.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoListModule.class)
public interface VideoListComponent {
    void inject(VideoListFragment fragment);
}
