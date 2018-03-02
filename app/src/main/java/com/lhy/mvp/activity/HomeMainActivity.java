package com.lhy.mvp.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.downloaderlib.FileDownloader;
import com.lhy.mvp.R;
import com.lhy.mvp.adapter.FragmentAdapter;
import com.lhy.mvp.fragment.MyCenterFragment;
import com.lhy.mvp.module.base.BaseActivity;
import com.lhy.mvp.module.news.main.NewsMainFragment;
import com.lhy.mvp.module.photo.main.PhotoMainFragment;
import com.lhy.mvp.module.video.main.VideoMainFragment;
import com.lhy.mvp.utils.SnackbarUtils;
import com.lhy.mvp.widget.NoScrollViewPager;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

public class HomeMainActivity extends BaseActivity {


    @BindView(R.id.viewpager_container)
    NoScrollViewPager viewpagerContainer;
    @BindView(R.id.viewSplit)
    View viewSplit;
    @BindView(R.id.foot_bar_home)
    RadioButton footBarHome;
    @BindView(R.id.foot_bar_im)
    RadioButton footBarIm;
    @BindView(R.id.foot_bar_trading)
    RadioButton footBarTrading;
    @BindView(R.id.main_footbar_user)
    RadioButton mainFootbarUser;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.viewSplitLine)
    View viewSplitLine;
    @BindView(R.id.textUnreadLabel)
    TextView textUnreadLabel;
    @BindView(R.id.layoutFooter)
    RelativeLayout layoutFooter;
    // 本来想用这个来存储Fragment做切换，不过貌似fragment会被回收产生异常，估计内存占用太大
//    private SparseArray<Fragment> mSparseFragments = new SparseArray<>();
    private SparseArray<String> mSparseTags = new SparseArray<>();
    private long mExitTime = 0;
    private int mItemId = -1;

    private int currIndex;
    private ArrayList<String> fragmentTags;
    private NewsMainFragment mHomePageFragment;
    private PhotoMainFragment mFragmentTwo;
    private VideoMainFragment mFragmentThree;
    private MyCenterFragment mFragmentFour;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentAdapter homePageFragmentAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home_main;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
        viewpagerContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currIndex = position;
                switch (position) {
                    case 0:
                        group.check(R.id.foot_bar_home);
                        break;
                    case 1:
                        group.check(R.id.foot_bar_im);
                        break;
                    case 2:
                        group.check(R.id.foot_bar_trading);
                        break;
                    case 3:
                        group.check(R.id.main_footbar_user);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.foot_bar_home:
                        viewpagerContainer.setCurrentItem(0, false);
                        break;
                    case R.id.foot_bar_im:
                        viewpagerContainer.setCurrentItem(1, false);
                        break;
                    case R.id.foot_bar_trading:
                        viewpagerContainer.setCurrentItem(2, false);
                        break;
                    case R.id.main_footbar_user:
                        viewpagerContainer.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }
            }
        });


        mSparseTags.put(R.id.nav_news, "News");
        mSparseTags.put(R.id.nav_photos, "Photos");
        mSparseTags.put(R.id.nav_videos, "Videos");

        _getPermission();


        fragmentTags = new ArrayList<>(Arrays.asList("OneFragment", "TwoFragment", "ThreeFragment", "FourFragment"));
        mHomePageFragment = new NewsMainFragment();
        mFragmentTwo = new PhotoMainFragment();
        mFragmentThree = new VideoMainFragment();
        mFragmentFour = new MyCenterFragment();
        mFragmentList.add(mHomePageFragment);
        mFragmentList.add(mFragmentTwo);
        mFragmentList.add(mFragmentThree);
        mFragmentList.add(mFragmentFour);
        homePageFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        viewpagerContainer.setOffscreenPageLimit(3);
        viewpagerContainer.setAdapter(homePageFragmentAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {


    }


    @Override
    public void onBackPressed() {
        _exit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void _getPermission() {
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            final File dir = new File(FileDownloader.getDownloadDir());
                            if (!dir.exists() || !dir.isDirectory()) {
                                dir.delete();
                                dir.mkdirs();
                            }
                        } else {
                            SnackbarUtils.showSnackbar(HomeMainActivity.this, "获取读写文件权限失败", true);
                        }
                    }
                });
    }


    /**
     * 退出
     */
    private void _exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


}
