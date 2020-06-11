package com.hc.libcommon.utils;

import android.util.DisplayMetrics;

import com.hc.libcommon.global.AppGlobals;

/**
 * Created by hcw  on 2020/6/11
 * 类描述：
 * all rights reserved
 */
public class PixUtils {

    public static int dp2px(int dpValue) {
        DisplayMetrics metrics = AppGlobals.getApplication().getResources().getDisplayMetrics();
        return (int) (metrics.density * dpValue + 0.5f);
    }

    public static int getScreenWidth() {
        DisplayMetrics metrics = AppGlobals.getApplication().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metrics = AppGlobals.getApplication().getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
