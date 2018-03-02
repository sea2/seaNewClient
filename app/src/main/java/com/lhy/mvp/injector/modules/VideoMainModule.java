package com.lhy.mvp.injector.modules;

import com.lhy.mvp.adapter.ViewPagerAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.local.table.DaoSession;
import com.lhy.mvp.module.base.IRxBusPresenter;
import com.lhy.mvp.module.video.main.VideoMainFragment;
import com.lhy.mvp.module.video.main.VideoMainPresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/20.
 * 视频主界面 Module
 */
@Module
public class VideoMainModule {

    private final VideoMainFragment mView;

    public VideoMainModule(VideoMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideVideosPresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoMainPresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }
}
