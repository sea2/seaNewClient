package com.lhy.mvp.injector.modules;

import com.lhy.mvp.adapter.ViewPagerAdapter;
import com.lhy.mvp.injector.PerFragment;
import com.lhy.mvp.local.table.DaoSession;
import com.lhy.mvp.module.base.IRxBusPresenter;
import com.lhy.mvp.module.news.main.NewsMainFragment;
import com.lhy.mvp.module.news.main.NewsMainPresenter;
import com.lhy.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/20.
 * 新闻主页 Module
 */
@Module
public class NewsMainModule {

    private final NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession daoSession, RxBus rxBus) {
        return new NewsMainPresenter(mView, daoSession.getNewsTypeInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }
}
