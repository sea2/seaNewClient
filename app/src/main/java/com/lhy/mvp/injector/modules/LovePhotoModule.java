package com.lhy.mvp.injector.modules;

import com.lhy.mvp.local.table.DaoSession;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.lhy.mvp.adapter.BeautyPhotosAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.module.base.ILocalPresenter;
import com.lhy.mvp.module.manage.love.photo.LovePhotoFragment;
import com.lhy.mvp.module.manage.love.photo.LovePhotoPresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/13.
 * 图片收藏界面 Module
 */
@Module
public class LovePhotoModule {

    private final LovePhotoFragment mView;

    public LovePhotoModule(LovePhotoFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus) {
        return new LovePhotoPresenter(mView, daoSession.getBeautyPhotoInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new BeautyPhotosAdapter(mView);
    }
}
