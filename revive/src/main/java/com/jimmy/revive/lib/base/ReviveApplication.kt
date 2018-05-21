package com.jimmy.revive.lib.base

import android.app.Application

/**
 * 应用全局基类，控制整个app的生命周期
 *
 * Created by Jimmy on 2018/1/9.
 */
class ReviveApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}