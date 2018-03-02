package com.lhy.mvp.injector.modules;

import com.lhy.mvp.local.table.DaoSession;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.lhy.mvp.adapter.ManageAdapter;
import com.lhy.mvp.injector.PerActivity;
import com.lhy.mvp.module.news.channel.ChannelActivity;
import com.lhy.mvp.module.news.channel.ChannelPresenter;
import com.lhy.mvp.module.news.channel.IChannelPresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/31.
 * 管理
 */
@Module
public class ChannelModule {

    private final ChannelActivity mView;

    public ChannelModule(ChannelActivity view) {
        mView = view;
    }

    @Provides
    public BaseQuickAdapter provideManageAdapter() {
        return new ManageAdapter(mView);
    }

    @PerActivity
    @Provides
    public IChannelPresenter provideManagePresenter(DaoSession daoSession, RxBus rxBus) {
        return new ChannelPresenter(mView, daoSession.getNewsTypeInfoDao(), rxBus);
    }
}
