package com.jimmy.revive.lib.framework.util

import android.util.Log
import com.jimmy.revive.lib.BuildConfig

/**
 * log打印控制
 *
 * Created by Jimmy on 2018/1/9.
 */
val DEBUG = BuildConfig.DEBUG
val LOG_TAG = "revive"

fun i(msg: String) {
    Log.i(LOG_TAG, msg)
}

fun d(msg: String) {
    Log.d(LOG_TAG, msg)
}

fun w(msg: String) {
    Log.w(LOG_TAG, msg)
}

fun e(msg: String) {
    Log.e(LOG_TAG, msg)
}
