package com.jimmy.hellokotlin.lifecycle

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by Jimmy on 2017/12/21.
 */
open class TestLifecycleBaseActivity : AppCompatActivity() {

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

    fun log(event: String) {
        Log.d("TestLifecycle", getClassName() + ": $event $isFinishing")
    }

    fun getClassName() : String {
        return this::class.java.simpleName
    }

}