package com.lhy.mvp.injector.modules;

import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.module.base.IBasePresenter;
import com.lhy.mvp.module.news.photoset.PhotoNewsActivity;
import com.lhy.mvp.module.news.photoset.PhotoSetPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/29.
 * 图集 Module
 */
@Module
public class PhotoSetModule {

    private final PhotoNewsActivity mView;
    private final String mPhotoSetId;

    public PhotoSetModule(PhotoNewsActivity view, String photoSetId) {
        mView = view;
        mPhotoSetId = photoSetId;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePhotoSetPresenter() {
        return new PhotoSetPresenter(mView, mPhotoSetId);
    }
}
