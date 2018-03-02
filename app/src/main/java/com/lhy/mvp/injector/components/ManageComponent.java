package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.ChannelModule;
import com.lhy.mvp.module.news.channel.ChannelActivity;

import dagger.Component;

/**
 * Created by long on 2016/8/31.
 * 管理 Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ChannelModule.class)
public interface ManageComponent {
    void inject(ChannelActivity activity);
}
