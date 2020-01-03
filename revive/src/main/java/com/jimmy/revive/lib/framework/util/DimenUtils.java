package com.jimmy.revive.lib.framework.util;

import android.content.res.Resources;
import android.support.annotation.DimenRes;
import android.util.TypedValue;

import com.jimmy.revive.lib.base.ReviveContext;

/**
 * Created by meikai on 2017/09/12.
 */
public class DimenUtils {

    /**
     * 屏幕宽度
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 屏幕高度
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, ReviveContext.context.getResources().getDisplayMetrics()) + 0.5f);
    }

    /**
     * sp转px
     */
    public static int sp2px(float spVal) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, ReviveContext.context.getResources().getDisplayMetrics()) + 0.5f);
    }

    /**
     * px转dp
     */
    public static int px2dp(int pxVal) {
        final float scale = ReviveContext.context.getResources().getDisplayMetrics().density;
        return (int) ((pxVal / scale) + 0.5f);
    }

    /**
     * px转sp
     */
    public static int px2sp(int pxVal) {
        return (int) ((pxVal / ReviveContext.context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /**
     * dimen资源转px
     */
    public static int dimen2px(@DimenRes int resId) {
        return ReviveContext.context.getResources().getDimensionPixelSize(resId);
    }

}

