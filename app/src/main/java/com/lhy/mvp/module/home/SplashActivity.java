package com.lhy.mvp.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.lhy.mvp.R;
import com.lhy.mvp.activity.HomeMainActivity;
import com.lhy.mvp.activity.WebActivity;
import com.lhy.mvp.module.base.BaseActivity;
import com.lhy.mvp.widget.SimpleButton;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;


/**
 * 开机界面由守护神镇着，图是我盗用的别人的，我想买但看到团购都过期了〒_〒
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;
    @BindView(R.id.iv_adv)
    ImageView ivAdv;
    Subscription subscription;
    private boolean mIsSkip = false;
    private final int count = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        isUserDefinedColorForStatusBar = false;
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void updateViews(boolean isRefresh) {

        String urlImg = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512368884600&di=07033119ace402b8bb69b45578c0230a&imgtype=0&src=http%3A%2F%2F5.26923.com%2Fdownload%2Fpic%2F000%2F329%2F7cef44b6424b173da8025281fc3c7a78.jpg";
        Glide.with(SplashActivity.this)
                .load(urlImg)
                .centerCrop()
                //.error(R.drawable)
               // .placeholder(R.drawable.ic_github)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(ivAdv) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                        if (mHandler != null && mRunnable != null) {
                            mHandler.removeCallbacks(mRunnable);
                            mHandler = null;
                        }

                        ivAdv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mIsSkip = true;
                                Intent it = new Intent(SplashActivity.this, WebActivity.class);
                                it.putExtra("web_view_url", "https://www.baidu.com");
                                it.putExtra("fromActivity", SplashActivity.class);
                                startActivity(it);
                                finish();
                            }
                        });
                        countDown();
                    }
                });

        //最大启动页等待时间5秒，网络不好时候的一个处理
        mHandler.postDelayed(mRunnable, 5000);
    }

    private void _doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            startActivity(new Intent(SplashActivity.this, HomeMainActivity.class));
            finish();
        }
    }


    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            _doSkip();
        }
    };


    /**
     * 跳过按钮上的倒计时
     */
    private void countDown() {

        mSbSkip.setVisibility(View.VISIBLE);
        subscription = Observable.interval(0, 1, TimeUnit.SECONDS).take(count + 1).map(new Func1<Long, Long>() {
            @Override
            public Long call(Long aLong) {
                return count - aLong;
            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                //在call之前执行一些初始化操作
                Log.d(TAG, "doOnSubscribe: ");
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
                _doSkip();
            }

            @Override
            public void onError(Throwable e) {
                _doSkip();
                e.printStackTrace();
            }

            @Override
            public void onNext(Long aLong) { //接受到一条就是会操作一次UI
                Log.d(TAG, "onNext: " + aLong);
                mSbSkip.setText("跳过 " + String.valueOf(aLong).concat("S"));
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) subscription.unsubscribe();
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }

    @OnClick(R.id.sb_skip)
    public void onClick() {
        _doSkip();
    }
}
