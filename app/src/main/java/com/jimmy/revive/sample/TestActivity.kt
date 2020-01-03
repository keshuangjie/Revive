package com.jimmy.revive.sample

import android.os.Bundle
import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity
import kotlinx.android.synthetic.main.text_activity.*

/**
 * Created by Jimmy on 2019/5/28.
 */
class TestActivity : ReviveActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_activity)
        btn.setOnClickListener {
            tv.text = "${tv.text}1"
        }
    }

}