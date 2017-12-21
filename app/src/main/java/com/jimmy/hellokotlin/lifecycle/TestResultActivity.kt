package com.jimmy.hellokotlin.lifecycle

import android.os.Bundle
import com.jimmy.hellokotlin.R

/**
 * Created by Jimmy on 2017/12/21.
 */
class TestResultActivity : TestLifecycleBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_lifecycle_result_activity)
    }

}