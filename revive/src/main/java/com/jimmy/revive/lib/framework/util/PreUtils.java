package com.jimmy.revive.lib.framework.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.jimmy.revive.lib.base.ReviveContext;

/**
 * SharePreferences工具类
 * Created by Jimmy on 2018/06/15.
 */
public class PreUtils {

    private static final String PREFS_DEFAULT_PATH = "_voyager_pref";

    public static void setLong(String key, long value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void setInt(String key, int value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getString(String key, String defaultValue) {
        return getPrefs().getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return getPrefs().getInt(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return getPrefs().getLong(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getPrefs().getBoolean(key, defaultValue);
    }

    public static boolean contains(String key) {
        return getPrefs().contains(key);
    }

    private static SharedPreferences getPrefs() {
        return getPrefs(PREFS_DEFAULT_PATH);
    }

    /**
     * 得到 pref 实例。由于在多进程中使用 prefs 会造成数据不可预期的情况，所以我们禁止在多进程间使用 prefs 进行通信，并且给不同的进程不同的后缀，保证完全隔离。
     */
    public static SharedPreferences getPrefs(String prefPath) {
        return ReviveContext.context.getSharedPreferences(prefPath, Context.MODE_PRIVATE);
    }


    /**
     * 获取主进程中 存在 的SP
     * 仅用于 多进程启动时重置 MucangConfig.isDebug() 时使用
     */
    public static SharedPreferences getMainProcessPrefs() {

        // 第二个参数用 MODE_MULTI_PROCESS，修改开发者模式后，不再需要重启进程了，但需要重新开始录制
        return ReviveContext.context.getSharedPreferences(PREFS_DEFAULT_PATH, Context.MODE_MULTI_PROCESS);
    }

}
