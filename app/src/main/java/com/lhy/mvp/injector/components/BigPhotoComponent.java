package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.BigPhotoModule;
import com.lhy.mvp.module.photo.bigphoto.BigPhotoActivity;

import dagger.Component;

/**
 * Created by long on 2016/9/27.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = BigPhotoModule.class)
public interface BigPhotoComponent {
    void inject(BigPhotoActivity activity);
}
