package com.lhy.mvp.bean;

import com.dl7.recycler.entity.MultiItemEntity;

/**
 * Created by lhy on 2017/12/7.
 */

public class MyItemInfo extends MultiItemEntity {


    public static final int ITEM_TYPE_NORMAL = 1;
    public static final int ITEM_TYPE_EMPTY = 2;
    MyItemOneInfo myItemOneInfo;
    int type;

    public MyItemInfo(int itemType) {
        super(itemType);
        type = itemType;
    }

    public MyItemOneInfo getMyItemOneInfo() {
        return myItemOneInfo;
    }

    public void setMyItemOneInfo(MyItemOneInfo myItemOneInfo) {
        this.myItemOneInfo = myItemOneInfo;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
