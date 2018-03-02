package com.lhy.mvp.injector.modules;

import com.lhy.mvp.local.table.DaoSession;
import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.local.table.VideoInfo;
import com.lhy.mvp.module.video.player.IVideoPresenter;
import com.lhy.mvp.module.video.player.VideoPlayerActivity;
import com.lhy.mvp.module.video.player.VideoPlayerPresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/11/30.
 * Video Module
 */
@Module
public class VideoPlayerModule {

    private final VideoPlayerActivity mView;
    private final VideoInfo mVideoData;

    public VideoPlayerModule(VideoPlayerActivity view, VideoInfo videoData) {
        this.mView = view;
        this.mVideoData = videoData;
    }

    @PerActivity
    @Provides
    public IVideoPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new VideoPlayerPresenter(mView, daoSession.getVideoInfoDao(), rxBus, mVideoData, daoSession.getDanmakuInfoDao());
    }

}
