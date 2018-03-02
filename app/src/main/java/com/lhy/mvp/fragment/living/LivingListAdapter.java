package com.lhy.mvp.fragment.living;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lhy.mvp.R;

import java.util.List;

/**
 * Created by admin on 2017/2/14.
 */

public class LivingListAdapter extends SimpleRecyclerAdapter<LivingItems.DataBean.ItemsBean> {
    private int type = DATUTYPE;

    public LivingListAdapter(List<LivingItems.DataBean.ItemsBean> list, Context ctx) {
        super(list, ctx);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type == DATUTYPE) {
            LivingListHolder datuHolder = new LivingListHolder(View.inflate(ctx, R.layout.item_list_living, null));
            return datuHolder;
        } else {
            LivingListHolder datuHolder = new LivingListHolder(View.inflate(ctx, R.layout.item_list_living, null));
            return datuHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (type == DATUTYPE)
            handleDatu(holder, position);
    }


    private void handleDatu(RecyclerView.ViewHolder holder, int position) {
        LivingListHolder mLivingListHolder = (LivingListHolder) holder;
        LivingItems.DataBean.ItemsBean itemsBean = list.get(position);
        mLivingListHolder.nikeNameTv.setText(itemsBean.getNickName());
        if (itemsBean.getCity() != null && !itemsBean.getCity().equals("")) {
            mLivingListHolder.locationTv.setText(itemsBean.getCity());
            mLivingListHolder.locationTv.setVisibility(View.VISIBLE);
        } else mLivingListHolder.locationTv.setVisibility(View.GONE);
        Glide.with(ctx).load(itemsBean.getPhoto()).into(mLivingListHolder.iv_photo);
        Glide.with(ctx).load(itemsBean.getAvatar()).into(mLivingListHolder.iv_head);
    }

    class LivingListHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo;
        ImageView iv_head;
        TextView nikeNameTv;
        TextView locationTv;

        public LivingListHolder(View itemView) {
            super(itemView);
            locationTv = (TextView) itemView.findViewById(R.id.locationTv);
            nikeNameTv = (TextView) itemView.findViewById(R.id.nickNameTv);
            iv_head = (ImageView) itemView.findViewById(R.id.headIv);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }

    public static final int DATUTYPE = 1;
}
