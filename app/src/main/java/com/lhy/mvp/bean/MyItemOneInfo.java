package com.lhy.mvp.bean;

/**
 * Created by lhy on 2017/12/7.
 */

public class MyItemOneInfo {

    String title;
    int imageInt;
    String scheme;
    String imgsrc;

    public MyItemOneInfo(String title, int imageInt, String scheme, String imgsrc) {
        this.title = title;
        this.imageInt = imageInt;
        this.scheme = scheme;
        this.imgsrc = imgsrc;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
