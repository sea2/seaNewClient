package com.lhy.mvp.module.news.special;

import com.lhy.mvp.api.bean.SpecialInfo;
import com.lhy.mvp.adapter.item.SpecialItem;
import com.lhy.mvp.module.base.IBaseView;
import com.lhy.mvp.api.bean.SpecialInfo;

import java.util.List;

/**
 * Created by long on 2016/8/26.
 * 专题View接口
 */
public interface ISpecialView extends IBaseView {

    /**
     * 显示数据
     * @param specialItems 新闻
     */
    void loadData(List<SpecialItem> specialItems);

    /**
     * 添加头部
     * @param specialBean
     */
    void loadBanner(SpecialInfo specialBean);
}
