package com.jimmy.revive.sample.lifecycle

import android.os.Bundle
import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity

/**
 * Created by Jimmy on 2017/12/21.
 */
class TestResultActivity : ReviveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_lifecycle_result_activity)
    }

}