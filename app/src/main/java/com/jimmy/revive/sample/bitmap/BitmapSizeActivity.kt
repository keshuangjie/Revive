package com.jimmy.revive.sample.bitmap

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity
import com.jimmy.revive.lib.framework.util.DimenUtils

/**
 * bitmap 大小测试
 *
 * Created by Jimmy on 2019-11-19.
 */
class BitmapSizeActivity : ReviveActivity() {

    private val tag = "BitmapSizeActivity"
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView = ImageView(this)
        setContentView(imageView)
        decodeResource()
        setImageResource()
        reuseBitmap()
//        matrixBitmap()
    }


    /**
     * 直接设置图片资源，计算内存大小
     */
    private fun setImageResource() {
        imageView.setImageResource(R.drawable.test_bitmap_size_webp)
        Log.e(tag, "image drawable: ${imageView.drawable}")
        if (imageView.drawable is BitmapDrawable) {
            Log.e(tag, "image setImageResource bitmap size: ${(imageView.drawable as BitmapDrawable)
                    .bitmap?.byteCount}")
        }
    }

    /**
     * 通过BitmapFactory.decodeResource，计算内存大小
     */
    private fun decodeResource() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test_bitmap_size)
        imageView.setImageBitmap(bitmap)
        Log.e(tag, "BitmapFactory.decodeResource bitmap size: ${bitmap.byteCount}")
    }

    /**
     * 使用inBitmap复用bitmap内存
     */
    private fun reuseBitmap() {
        val options = BitmapFactory.Options()
        // 图片复用，这个属性必须设置；
        options.inMutable = true
        // 手动设置缩放比例
        options.inDensity = 320
        options.inTargetDensity = 320
        val bitmap = BitmapFactory.decodeResource(resources,
                R.drawable.test_bitmap_size, options)
        // 对象内存地址；
        Log.e(tag, "reuseBitmap bitmap = $bitmap, byteCount=${bitmap.byteCount}, " +
                "AllocationByteCount=${bitmap.allocationByteCount}")
        options.inBitmap = bitmap
        options.inDensity = 320
        // 设置缩放宽高为原始宽高一半
        options.inTargetDensity = 160
        options.inMutable = true
        val bitmapReuse = BitmapFactory.decodeResource(resources,
                R.drawable.test_bitmap_size, options)
        // 复用对象的内存地址；
        Log.e(tag, "reuseBitmap bitmapReuse = $bitmapReuse, byteCount=${bitmapReuse
                .byteCount}, " +
                "allocationByteCount=${bitmapReuse.allocationByteCount}")
    }

    private fun matrixBitmap() {
        imageView.layoutParams = FrameLayout.LayoutParams(DimenUtils.dp2px(300f),
                DimenUtils.dp2px(300f))
        imageView.setImageResource(R.drawable.test_bitmap_ercode)
        if (imageView.drawable is BitmapDrawable) {
            Log.e(tag, "matrixBitmap bitmap size: ${(imageView.drawable as BitmapDrawable)
                    .bitmap?.byteCount}")
        }
    }


}