package com.lhy.mvp.injector.modules;

import com.lhy.mvp.adapter.ViewPagerAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.local.table.DaoSession;
import com.lhy.mvp.module.base.IRxBusPresenter;
import com.lhy.mvp.module.photo.main.PhotoMainFragment;
import com.lhy.mvp.module.photo.main.PhotoMainPresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/20.
 * 图片主界面 Module
 */
@Module
public class PhotoMainModule {

    private final PhotoMainFragment mView;

    public PhotoMainModule(PhotoMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePhotosPresenter(DaoSession daoSession, RxBus rxBus) {
        return new PhotoMainPresenter(mView, daoSession.getBeautyPhotoInfoDao(), rxBus);
    }
}
