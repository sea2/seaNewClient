package com.lhy.mvp.fragment.living;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by admin on 2017/2/9.
 */

public class LivingFragment extends BaseLivingListFragment {
    private LivingListAdapter mLivingListAdapter;


    public static LivingFragment newInstance() {
        LivingFragment fragment = new LivingFragment();
        return fragment;
    }


    @Override
    protected void initChildView() {
        super.initChildView();
        mLivingListAdapter = new LivingListAdapter(list, a);
        getRecycler().setAdapter(mLivingListAdapter);
        getSwip().setRefreshing(true);

        loadChildData();
    }

    int page = 1;

    private void loadChildData() {
        Retrofit retrofit;
        Retrofit xingYanretrofit;
        retrofit = new Retrofit.Builder().baseUrl("http://api.m.panda.tv/").addConverterFactory(GsonConverterFactory.create()).build();
        xingYanretrofit = new Retrofit.Builder().baseUrl("http://m.api.xingyan.panda.tv/").addConverterFactory(GsonConverterFactory.create()).build();

        xingYanretrofit.create(LivingService.class).getItems(page + "").enqueue(
                new Callback<LivingItems>() {
                    @Override
                    public void onResponse(Call<LivingItems> call, Response<LivingItems> response) {
                        LivingItems body = response.body();
                        if (page == 1) {
                            getSwip().setRefreshing(false);
                        }
                        handleContainter(body);

                    }

                    @Override
                    public void onFailure(Call<LivingItems> call, Throwable t) {
                        toast("失败--》" + t.getMessage());
                        if (page == 1) {
                            getSwip().setRefreshing(false);
                        }
                    }
                }
        );
    }

    List<LivingItems.DataBean.ItemsBean> list = new ArrayList<>();

    private void handleContainter(LivingItems body) {
        List<LivingItems.DataBean.BannersBean> banners = body.getData().getBanners();
        List<LivingItems.DataBean.ItemsBean> items = body.getData().getItems();
        if (page == 1) {
            list.clear();
        }
        list.addAll(items);
        if (items.size() != 20) {
            setLoadDataSuccess(true);
        } else {
            page++;
        }
        getRecycler().getAdapter().notifyDataSetChanged();

    }

    @Override
    protected void pullMore() {
        super.pullMore();
        page = 1;
        setLoadDataSuccess(false);
        loadChildData();
    }




    @Override
    public void onItemClick(RecyclerView recyclerView, View view, int position) {
        super.onItemClick(recyclerView, view, position);
        if (position >= getRecycler().getHeadCount() && position < list.size() + getRecycler().getHeadCount()) {
            Intent intent = new Intent(a, LivingPlayActivity.class);
            intent.putExtra("list", new WarpParcl(list));
            intent.putExtra("position", position - getRecycler().getHeadCount());
            startActivity(intent);
        }
    }
}
