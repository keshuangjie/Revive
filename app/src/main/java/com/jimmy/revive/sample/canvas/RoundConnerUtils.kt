package com.jimmy.revive.sample.canvas

import android.graphics.*

/**
 * Created by Jimmy on 2019/3/24.
 */
object RoundConnerUtils {

    fun createBitmap(inBitmap: Bitmap, roundingRadius: Int): Bitmap {
//        val toTransform = Bitmap.createBitmap(inBitmap.width, inBitmap.height, Bitmap.Config.ARGB_8888)
//        toTransform.isPremultiplied = true
//        toTransform.setHasAlpha(true)
//        Canvas(toTransform).drawBitmap(inBitmap, 0f , 0f , null)

        val result = Bitmap.createBitmap(inBitmap.width, inBitmap.height, Bitmap.Config.ARGB_8888)

        result.setHasAlpha(true)

        val shader = BitmapShader(inBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = shader
        val rect = RectF(0f, 0f, result.width.toFloat(), result.height.toFloat())

        val canvas = Canvas(result)
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        canvas.drawRoundRect(rect, roundingRadius.toFloat(), roundingRadius.toFloat(), paint)
        canvas.setBitmap(null)

        return result
    }

}