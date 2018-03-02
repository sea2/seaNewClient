package com.lhy.mvp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.lhy.mvp.R;
import com.lhy.mvp.module.base.BaseActivity;
import com.lhy.mvp.module.home.SplashActivity;

import butterknife.BindView;

public class WebActivity extends BaseActivity {

    @BindView(R.id.wv_load)
    WebView wvLoad;
    @BindView(R.id.myProgressBar)
    ProgressBar myProgressBar;
    @BindView(R.id.rl_webview)
    RelativeLayout rlWebview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String web_view_url;
    private String action;
    private Class fromActivity;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initInjector() {
        initIntent();
    }

    @Override
    protected void initViews() {


        wvLoad.getSettings().setJavaScriptEnabled(true);
        wvLoad.getSettings().setAppCacheEnabled(false);
        wvLoad.getSettings().setSupportZoom(true);
        wvLoad.getSettings().setSavePassword(false);
        if (Build.VERSION.SDK_INT >= 11) {
            wvLoad.getSettings().setBuiltInZoomControls(true);
            wvLoad.getSettings().setDisplayZoomControls(false); //隐藏Zoom缩放按钮
        }
        //add by xiehy 修复HTML页面加载WEBVIEW 显示问题 不使用加载百分百显示
        wvLoad.getSettings().setUseWideViewPort(true);
        wvLoad.getSettings().setLoadWithOverviewMode(true);
        wvLoad.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        //在Android5.0及5.0以上版本，WebView通过https访问的资源，该资源里面又通过http访问了别的资源，默认WebView阻止的后面的http资源的访问，报Mixed Content
        //以下设置为不阻止，可以访问
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wvLoad.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        ititTitle();
        initListener();
        hideLoading();
    }


    private void initIntent() {

        Intent it = getIntent();
        if (it != null) {
            action = it.getAction();
            web_view_url = it.getStringExtra("web_view_url");
            fromActivity = (Class) it.getSerializableExtra("fromActivity");
        }
    }


    private void ititTitle() {
        initToolBar(toolbar, true, "");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack();
            }
        });
    }

    private void initListener() {


        wvLoad.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                // 监听下载功能，当用户点击下载链接的时候，直接调用系统的浏览器来下载
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        //----------------------WebViewClient----------------------


        wvLoad.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url)) {
                    if ("open_login".equals(url)) {
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }



            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //用javascript隐藏系统定义的404页面信息
                String data = "出错了哦~\n您的页面找不到了！";
                view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);

            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });

        //----------------------WebChromeClient----------------------
        WebChromeClient wvcc = new WebChromeClient() {
            //add by xiehy V4.5版本添加进度条功能
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                myProgressBar.setProgress(newProgress);
                myProgressBar.postInvalidate();
                if (newProgress == 100) {
                    myProgressBar.setVisibility(View.GONE);

                }
            }

            //end add
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                toolbar.setTitle(title);

            }

        };
        wvLoad.setWebChromeClient(wvcc);


        wvLoad.loadUrl(web_view_url);
    }


    public void doBack() {
        if (wvLoad != null && wvLoad.canGoBack()) {
            wvLoad.goBack();
        } else {
            if (fromActivity != null && fromActivity.getSimpleName().equals(SplashActivity.class.getSimpleName()))
                startActivity(new Intent(WebActivity.this, HomeMainActivity.class));
            finish();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            doBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        if (wvLoad != null) {
            try {
                rlWebview.removeView(wvLoad);
                wvLoad.clearHistory();
                wvLoad.clearCache(true);
                wvLoad.pauseTimers();
                wvLoad.destroy();
                wvLoad = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }


}
