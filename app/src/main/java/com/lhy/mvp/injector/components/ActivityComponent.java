package com.lhy.mvp.injector.components;

import android.app.Activity;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by long on 2016/8/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
