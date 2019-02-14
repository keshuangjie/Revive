package com.jimmy.revive.sample.lifecycle

import android.content.Intent
import android.os.Bundle
import com.jimmy.revive.lib.base.ReviveActivity
import com.jimmy.revive.lib.framework.util.w

/**
 * Created by Jimmy on 2017/12/21.
 */
open class TestLifecycleBaseActivity : ReviveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        log("onNewIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        log("onActivityResult")
    }

    fun log(event: String) {
        w(getClassName() + ": $event $isFinishing")
    }

    fun getClassName() : String {
        return this::class.java.simpleName
    }

}