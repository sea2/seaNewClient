package com.lhy.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lhy.mvp.R;
import com.lhy.mvp.api.bean.WelfarePhotoInfo;
import com.lhy.mvp.local.table.BeautyPhotoInfo;
import com.lhy.mvp.module.photo.bigphoto.BigPhotoActivity;
import com.lhy.mvp.utils.DefIconFactory;
import com.lhy.mvp.utils.ImageLoader;
import com.lhy.mvp.utils.StringUtils;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by long on 2016/10/11.
 * 福利图适配器
 */
public class WelfarePhotoAdapter extends BaseQuickAdapter<WelfarePhotoInfo> {

    // 图片的宽度
    private int mPhotoWidth;


    public WelfarePhotoAdapter(Context context) {
        super(context);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int marginPixels = context.getResources().getDimensionPixelOffset(R.dimen.photo_margin_width);
        mPhotoWidth = widthPixels / 2 - marginPixels;
    }

    public WelfarePhotoAdapter(Context context, List<WelfarePhotoInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_welfare_photo;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final WelfarePhotoInfo item) {
        final ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int photoHeight = StringUtils.calcPhotoHeight(item.getPixel(), mPhotoWidth);
        // 返回的数据有像素分辨率，根据这个来缩放图片大小
        final ViewGroup.LayoutParams params = ivPhoto.getLayoutParams();
        params.width = mPhotoWidth;
        params.height = photoHeight;
        ivPhoto.setLayoutParams(params);
        ImageLoader.loadFitCenter(mContext, item.getUrl(), ivPhoto, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title, item.getCreatedAt());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BeautyPhotoInfo> mBeautyPhotoInfoList = new ArrayList<BeautyPhotoInfo>();
                BeautyPhotoInfo mBeautyPhotoInfo = new BeautyPhotoInfo();
                mBeautyPhotoInfo.setDocid(item.getId());
                mBeautyPhotoInfo.setPixel(item.getPixel());
                mBeautyPhotoInfo.setIsPraise(true);
                mBeautyPhotoInfo.setTitle(item.getWho());
                mBeautyPhotoInfo.setImgsrc(item.getUrl());
                mBeautyPhotoInfoList.add(mBeautyPhotoInfo);
                BigPhotoActivity.launch(mContext, mBeautyPhotoInfoList, holder.getAdapterPosition());

            }
        });
    }
}