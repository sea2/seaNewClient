package com.lhy.mvp.injector.modules;

import com.lhy.mvp.local.table.DaoSession;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.lhy.mvp.adapter.VideoLoveAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.module.base.ILocalPresenter;
import com.lhy.mvp.module.manage.love.video.LoveVideoFragment;
import com.lhy.mvp.module.manage.love.video.LoveVideoPresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/13.
 * Video收藏
 */
@Module
public class LoveVideoModule {

    private final LoveVideoFragment mView;

    public LoveVideoModule(LoveVideoFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new LoveVideoPresenter(mView, daoSession.getVideoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new VideoLoveAdapter(mView.getContext());
    }
}
