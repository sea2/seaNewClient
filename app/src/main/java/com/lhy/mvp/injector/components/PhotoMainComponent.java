package com.lhy.mvp.injector.components;


import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.PhotoMainModule;
import com.lhy.mvp.module.photo.main.PhotoMainFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/20.
 * 图片 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = PhotoMainModule.class)
public interface PhotoMainComponent {
    void inject(PhotoMainFragment fragment);
}
