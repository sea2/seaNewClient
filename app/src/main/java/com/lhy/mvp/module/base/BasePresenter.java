package com.lhy.mvp.module.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by lhy on 2017/12/13.
 */

public abstract  class BasePresenter <T> {
    protected Reference<T> mViewRef; //View接口类型的弱引用

    public void attachView(T view){
        mViewRef = new WeakReference<T>(view); //建立关联
    }

    protected T getView(){
        return mViewRef.get(); //获取View
    }

    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null; //判断是否与View建立关联
    }

    public void detachView(){
        if(mViewRef != null){
            mViewRef.clear(); //解除关联
            mViewRef = null;
        }
    }
}
