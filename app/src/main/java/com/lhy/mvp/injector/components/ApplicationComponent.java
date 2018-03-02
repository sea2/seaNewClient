package com.lhy.mvp.injector.components;

import android.content.Context;

import com.lhy.mvp.local.table.DaoSession;
import com.lhy.mvp.injector.modules.ApplicationModule;
import com.lhy.mvp.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by long on 2016/8/19.
 * Application Component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

//    void inject(BaseActivity baseActivity);

    // provide
    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}
