package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.WelfarePhotoModule;
import com.lhy.mvp.module.photo.welfare.WelfareListFragment;

import dagger.Component;

/**
 * Created by long on 2016/10/11.
 * 福利图片界面 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = WelfarePhotoModule.class)
public interface WelfarePhotoComponent {
    void inject(WelfareListFragment fragment);
}
