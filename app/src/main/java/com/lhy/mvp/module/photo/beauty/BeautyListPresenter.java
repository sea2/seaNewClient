package com.lhy.mvp.module.photo.beauty;

import android.os.Handler;

import com.lhy.mvp.api.RetrofitService;
import com.lhy.mvp.local.table.BeautyPhotoInfo;
import com.lhy.mvp.module.base.IBasePresenter;
import com.lhy.mvp.module.base.ILoadDataView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by long on 2016/9/5.
 * 美图 Presenter
 */
public class BeautyListPresenter implements IBasePresenter {

    private final ILoadDataView mView;

    private int mPage = 0;

    public BeautyListPresenter(ILoadDataView view) {
        this.mView = view;
    }

    @Override
    public void getData(boolean isRefresh) {
        // 因为网易这个原接口参数一大堆，我只传了部分参数，返回的数据会出现图片重复的情况，请不要在意这个问题- -
      /*  RetrofitService.getBeautyPhoto(mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<List<BeautyPhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<BeautyPhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(List<BeautyPhotoInfo> photoList) {
                        mView.loadData(photoList);
                        mPage++;
                    }
                });*/
        mView.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<BeautyPhotoInfo> photoList = new ArrayList<BeautyPhotoInfo>();
                BeautyPhotoInfo mBeautyPhotoInfo = new BeautyPhotoInfo();
                mBeautyPhotoInfo.setDocid("BQSOIFA29001IFA3");
                mBeautyPhotoInfo.setTitle("长发美女  郭碧婷");
                mBeautyPhotoInfo.setDownload(false);
                mBeautyPhotoInfo.setImgsrc("http://dmr.nosdn.127.net/w_TEGSpvSxskhGnIuNAt5Q==/6896093022273156436.jpg");
                mBeautyPhotoInfo.setIsLove(true);
                mBeautyPhotoInfo.setIsPraise(true);
                mBeautyPhotoInfo.setPixel("700*467");

                /**
                 * adtype : 0
                 * boardid : comment_bbs
                 * clkNum : 0
                 * digest : 长发美女  郭碧婷
                 * docid : BQSOIFA29001IFA3
                 * downTimes : 1264
                 * img : http://dmr.nosdn.127.net/w_TEGSpvSxskhGnIuNAt5Q==/6896093022273156436.jpg
                 * imgType : 0
                 * imgsrc : http://dmr.nosdn.127.net/w_TEGSpvSxskhGnIuNAt5Q==/6896093022273156436.jpg
                 * picCount : 0
                 * pixel : 700*467
                 * program : HY
                 * prompt : 成功为您推荐20条新内容
                 * recType : 0
                 * replyCount : 74
                 * replyid : BQSOIFA29001IFA3
                 * source : 堆糖网
                 * title : 长发美女  郭碧婷
                 * upTimes : 4808
                 */
                photoList.add(mBeautyPhotoInfo);

                photoList.add(mBeautyPhotoInfo);

                mView.hideLoading();
                mView.loadData(photoList);
                mPage++;
            }
        }, 2000);


    }

    @Override
    public void getMoreData() {
        RetrofitService.getBeautyPhoto(mPage)
                .compose(mView.<List<BeautyPhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<BeautyPhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<BeautyPhotoInfo> photoList) {
                        mView.loadMoreData(photoList);
                        mPage++;
                    }
                });
    }
}
