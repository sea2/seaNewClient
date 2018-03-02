package com.lhy.mvp.injector.modules;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.lhy.mvp.adapter.WelfarePhotoAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.module.base.IBasePresenter;
import com.lhy.mvp.module.photo.welfare.WelfareListFragment;
import com.lhy.mvp.module.photo.welfare.WelfareListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/10/11.
 * 福利图片界面 Module
 */
@Module
public class WelfarePhotoModule {

    private final WelfareListFragment mView;

    public WelfarePhotoModule(WelfareListFragment view) {
        this.mView = view;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new WelfareListPresenter(mView);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new WelfarePhotoAdapter(mView.getContext());
    }
}
