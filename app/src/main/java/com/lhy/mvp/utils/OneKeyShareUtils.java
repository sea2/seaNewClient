package com.lhy.mvp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.lhy.mvp.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 分享平台
 *
 * @author xiehongyun
 */

public class OneKeyShareUtils {


    public String defaultUrl = "http://www.lhynews.com";
    private static String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/lhynews/";
    private String tag;


    public OneKeyShareUtils() {

    }


    /**
     * ShareSDK集成方法有两种</br>
     * 1、第一种是引用方式，例如引用onekeyshare项目，onekeyshare项目再引用mainlibs库</br>
     * 2、第二种是把onekeyshare和mainlibs集成到项目中，本例子就是用第二种方式</br> 请看“ShareSDK
     * 使用说明文档”，SDK下载目录中 </br> 或者看网络集成文档
     * http://wiki.mob.com/Android_%E5%BF%AB%E9%
     * 80%9F%E9%9B%86%E6%88%90%E6%8C%87%E5%8D%97
     * 3、混淆时，把sample或者本例子的混淆代码copy过去，在proguard-project.txt文件中
     * <p/>
     * <p/>
     * 平台配置信息有三种方式： 1、在我们后台配置各个微博平台的key
     * 2、在代码中配置各个微博平台的key，http://mob.com/androidDoc
     * /cn/sharesdk/framework/ShareSDK.html
     * 3、在配置文件中配置，本例子里面的assets/ShareSDK.conf,
     */


    public void share(Context context, String customText, String url, String imageUrl, String title, String share_id) {
        if (TextUtils.isEmpty(customText) || TextUtils.isEmpty(url) || TextUtils.isEmpty(imageUrl) || TextUtils.isEmpty(title)) {
            Log.e(this.getClass().getSimpleName(), "分享参数配置错误~");
            return;
        }
        if (TextUtils.isEmpty(title)) {
            title = context.getString(R.string.app_name);
        }
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "http://static.lhynews.com/Public/Static/201404/images/logo.jpg";
        }
        saveImage(context,customText, url, imageUrl, title, share_id);
    }








    /**
     * @param customText
     * @param url
     * @param imageUrl
     * @param imagePath
     * @param title
     * @Title: doShare
     * @Description: 注意：分享图片URL中，腾讯微博，QQ空间不能分享带有端口号的URL（平台限制）
     * @author xiehy
     */
    private void doShare(Context context, final String customText, final String url, String imageUrl, final String imagePath, final String title) {
        doShare(context, customText, url, imageUrl, imagePath, title, "");
    }

    private void doShare(Context context, final String customText, final String url, String imageUrl, final String imagePath, final String title, final String share_id) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(customText + url);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        if (!TextUtils.isEmpty(imagePath) && new File(imagePath).exists()) {
            oks.setImagePath(imagePath);
        } else {
            oks.setImageUrl(imageUrl);
        }
        oks.setSilent(false);
        oks.setDialogMode(true);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(customText);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(defaultUrl);

        // 启动分享GUI


        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {


            @Override
            public void onShare(Platform platform, ShareParams paramsToShare) {
                String name = platform.getName();
                Log.i(this.getClass().getSimpleName(), "分享渠道name:" + name);
                if (WechatMoments.NAME.equals(name)) {
                    paramsToShare.setTitle(customText);
                }

                if (WechatMoments.NAME.equals(name)) {
                    //微信盆友圈
                } else if (ShortMessage.NAME.equals(name)) {
                    //短信
                } else if (SinaWeibo.NAME.equals(name)) {
                    //新浪微博
                } else if (Wechat.NAME.equals(name)) {
                    //微信好友
                } else if (QQ.NAME.equals(name)) {
                    //qq
                } else if (QZone.NAME.equals(name)) {
                    //qq
                } else if (WechatFavorite.NAME.equals(name)) {
                    //qq
                }

            }

        });
        if (!TextUtils.isEmpty(share_id)) {
            oks.setCallback(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    String name = platform.getName();

                    Log.e(this.getClass().getSimpleName(), i + "");
                    if (WechatMoments.NAME.equals(name)) {
                        //微信盆友圈


                    }

                }

                @Override
                public void onError(Platform platform, int i, Throwable throwable) {
                    Log.e(this.getClass().getSimpleName(), i + "");
                }

                @Override
                public void onCancel(Platform platform, int i) {
                    Log.e(this.getClass().getSimpleName(), i + "");
                }
            });
        }
        oks.show(context);
    }

    /**
     * 保存图片到本地 腾讯微博分享图片，URL需为网络图片，如果为公司服务器图片，需没有端口号方可分享成功
     * 目前公司图片URL有端口号，所以将图片保存到本地，否则腾讯微博分享不成功
     *
     * @param customText
     * @param url
     * @param imageUrl
     */

    private void saveImage(Context context, final String customText, final String url, final String imageUrl, final String title) {
        saveImage(context, customText, url, imageUrl, title, "");
    }


    private void saveImage(final Context context, final String customText, final String url, final String imageUrl, final String title, final String share_id) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String mFileName = "share.jpg";
                    InputStream imageIs = getImageStream(context,imageUrl);
                    if (imageIs != null) {
                        Bitmap mBitmap = BitmapFactory.decodeStream(imageIs);
                        if (mBitmap != null) {
                            String path = saveFile(context,mBitmap, mFileName);
                            if (!TextUtils.isEmpty(path)) {
                                doShare(context,customText, url, imageUrl, path, title, share_id);
                                mBitmap.recycle();
                                Log.i(this.getClass().getSimpleName(), "图片保存成功！");
                            } else {
                                doShare(context,customText, url, imageUrl, null, title, share_id);
                            }
                        } else {
                            doShare(context,customText, url, imageUrl, null, title, share_id);
                        }
                    } else {
                        doShare(context,customText, url, imageUrl, null, title, share_id);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    private String saveFile(Context context, Bitmap bm, String fileName) throws IOException {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ALBUM_PATH = context.getExternalCacheDir() + "/lhynews/";
        }
        File dirFile = new File(ALBUM_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }

        // File dataPath = context.getFilesDir();
        // 本来想如果没有SD卡，直接存放到data/data目录，但考虑到One key share是第三方类库，有可能无权限获取图片

        File myCaptureFile = new File(dirFile.getAbsolutePath() + "/" + fileName);
        if (myCaptureFile.exists()) {
            myCaptureFile.delete();
        }

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            return myCaptureFile.getPath();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get image from newwork
     *
     * @return InputStream
     * @throws Exception
     */
    private InputStream getImageStream(Context context, String imageUrl) throws Exception {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");


            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                return conn.getInputStream();
            }
        } catch (Exception e) {
            return context.getResources().getAssets().open("pic_default.png");
        }
        return null;
    }

}
