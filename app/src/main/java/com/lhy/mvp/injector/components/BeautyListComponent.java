package com.lhy.mvp.injector.components;

import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.injector.modules.BeautyListModule;
import com.lhy.mvp.module.photo.beauty.BeautyListFragment;

import dagger.Component;

/**
 * Created by long on 2016/9/5.
 * 美图 PerFragment
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = BeautyListModule.class)
public interface BeautyListComponent {
    void inject(BeautyListFragment fragment);
}
