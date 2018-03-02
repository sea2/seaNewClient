package com.lhy.mvp.utils;


import android.content.Context;

/**
 * Created by admin on 2017/2/10.
 */

public class DimenUtils {
    public static int dp2px(int dp, Context context) {
        float v = context.getResources().getDisplayMetrics().density * dp + 0.5f;
        return (int) v;
    }
}
