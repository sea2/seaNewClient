package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.VideoPlayerModule;
import com.lhy.mvp.module.video.player.VideoPlayerActivity;

import dagger.Component;

/**
 * Created by long on 2016/11/30.
 * Video Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = VideoPlayerModule.class)
public interface VideoPlayerComponent {
    void inject(VideoPlayerActivity activity);
}
