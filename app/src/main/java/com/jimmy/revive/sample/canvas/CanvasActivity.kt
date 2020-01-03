package com.jimmy.revive.sample.canvas

import android.os.Bundle
import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity
import kotlinx.android.synthetic.main.canvas_activity.*

/**
 * Created by Jimmy on 2018/1/9.
 */
class CanvasActivity : ReviveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(CustomPaintView(this))
        setContentView(R.layout.canvas_activity)
        progressView.updateProgress(75f)
//        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.revive__test_canvas_cover)
//        imageView.setImageBitmap(BitmapUtil.getRoundedCornerBitmap(bitmap, 48))
//        imageView.setImageBitmap(RoundConnerUtils.createBitmap(bitmap, 96))
    }

}