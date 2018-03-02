package com.lhy.mvp.injector.modules;

import com.lhy.mvp.local.table.DaoSession;
import com.lhy.mvp.adapter.BaseVideoDLAdapter;
import com.lhy.mvp.adapter.VideoCacheAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.module.base.IRxBusPresenter;
import com.lhy.mvp.module.manage.download.cache.VideoCacheFragment;
import com.lhy.mvp.module.manage.download.cache.VideoCachePresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/16.
 * video缓存Module
 */
@Module
public class VideoCacheModule {

    private final VideoCacheFragment mView;

    public VideoCacheModule(VideoCacheFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoCachePresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseVideoDLAdapter provideAdapter(RxBus rxBus) {
        return new VideoCacheAdapter(mView.getContext(), rxBus);
    }
}
