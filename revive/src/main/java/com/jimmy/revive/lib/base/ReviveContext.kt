package com.jimmy.revive.lib.base

import android.content.Context

/**
 * Created by Jimmy on 2019/3/24.
 */
object ReviveContext {

    lateinit var context: Context

    @JvmStatic
    fun init(context: Context) {
        this.context = context
    }

}