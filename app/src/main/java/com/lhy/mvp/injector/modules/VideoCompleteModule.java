package com.lhy.mvp.injector.modules;

import com.lhy.mvp.local.table.DaoSession;
import com.lhy.mvp.adapter.BaseVideoDLAdapter;
import com.lhy.mvp.adapter.VideoCompleteAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.module.base.IRxBusPresenter;
import com.lhy.mvp.module.manage.download.complete.VideoCompleteFragment;
import com.lhy.mvp.module.manage.download.complete.VideoCompletePresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/16.
 * video 缓存完成 Module
 */
@Module
public class VideoCompleteModule {

    private final VideoCompleteFragment mView;

    public VideoCompleteModule(VideoCompleteFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoCompletePresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseVideoDLAdapter provideAdapter(RxBus rxBus) {
        return new VideoCompleteAdapter(mView.getContext(), rxBus);
    }
}
