package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.VideoMainModule;
import com.lhy.mvp.module.video.main.VideoMainFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/20.
 * 视频主界面 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VideoMainModule.class)
public interface VideoMainComponent {
    void inject(VideoMainFragment fragment);
}
