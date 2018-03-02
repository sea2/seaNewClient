package com.lhy.mvp.fragment.living;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.lhy.mvp.R;
import com.lhy.mvp.module.base.BaseActivity;
import com.lhy.mvp.utils.DimenUtils;

import java.util.ArrayList;
import java.util.List;

public class LivingPlayActivity extends BaseActivity implements ViewPager.OnPageChangeListener, LivingPlayFragment.OnFragmentInteractionListener {

    private ViewPager playpager;
    private RelativeLayout activityplay;
    private List<LivingItems.DataBean.ItemsBean> list;
    private int position;
    private List<Fragment> fragmentList;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_play;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        WarpParcl o = (WarpParcl) getIntent().getSerializableExtra("list");
        list = (List<LivingItems.DataBean.ItemsBean>) o.o;
        position = getIntent().getIntExtra("position", 0);
        this.activityplay = (RelativeLayout) findViewById(R.id.activity_play);
        this.playpager = (ViewPager) findViewById(R.id.playpager);
        // ViewPager通过什么实现的Scroller.scrollTo,ScrlloBys

        initData();
        initAdapter();
        initListenr();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isUserDefinedColorForStatusBar = false;
        setStatusBarTintResource(R.color.black);
        super.onCreate(savedInstanceState);
    }

    private void initListenr() {
        playpager.setOnPageChangeListener(this);
    }

    private void initAdapter() {
        playpager.setPageMargin(DimenUtils.dp2px(5, LivingPlayActivity.this));
        playpager.setAdapter(new SimplePagerSatusAdapter(getSupportFragmentManager(), fragmentList));
        playpager.setCurrentItem(position);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        for (LivingItems.DataBean.ItemsBean bean : list) {
            LivingPlayFragment playFragment = new LivingPlayFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", bean);
            playFragment.setArguments(bundle);
            fragmentList.add(playFragment);
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < fragmentList.size(); i++) {
            fragmentList.get(i).setUserVisibleHint(i == position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(int type) {
        switch (type) {
            case 1:
                hideLoading();
                break;
        }
    }
}
