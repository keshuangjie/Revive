package com.jimmy.revive.sample.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

/**
 * 绘制常规图形
 *
 * Created by Jimmy on 2018/1/9.
 */
class CustomPaintView(context: Context) : View(context) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas, Paint())
        drawRect(canvas, Paint())
        drawRoundRect(canvas, Paint())
        drawOval(canvas, Paint())
    }

    /**
     * 绘制圆形
     */
    fun drawCircle(canvas: Canvas, paint: Paint) {
        // 去除锯齿
        paint.isAntiAlias = true
        // 设置颜色
        paint.color = Color.parseColor("#123456")
        // 绘制圆
        canvas.drawCircle(200f, 200f, 100f, paint)

        // 设置环形style
        paint.style = Paint.Style.STROKE
        // 设置环形宽度
        paint.strokeWidth = 20f
        // 绘制环形
        canvas.drawCircle(500f, 200f, 90f, paint)
    }

    /**
     * 绘制矩形
     */
    fun drawRect(canvas: Canvas, paint: Paint) {
        // 去除锯齿
        paint.isAntiAlias = true
        // 设置颜色
        paint.color = Color.parseColor("#123456")
        // 绘制矩形
        canvas.drawRect(100f, 400f, 300f, 600f, paint)

        // 设置空心style
        paint.style = Paint.Style.STROKE
        // 设置环形宽度
        paint.strokeWidth = 20f
        // 绘制矩形
        canvas.drawRect(410f, 410f, 590f, 590f, paint)
    }

    /**
     * 绘制圆角矩形
     */
    @SuppressLint("NewApi")
    fun drawRoundRect(canvas: Canvas, paint: Paint) {
        // 去除锯齿
        paint.isAntiAlias = true
        // 设置颜色
        paint.color = Color.parseColor("#123456")
        // 绘制圆角矩形
        canvas.drawRoundRect(100f, 700f, 300f, 900f, 12f, 12f, paint)

        // 绘制空心圆角矩形
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f
        canvas.drawRoundRect(410f, 710f, 590f, 890f, 12f, 12f, paint)
    }

    /**
     * 绘制椭圆形
     */
    @SuppressLint("NewApi")
    fun drawOval(canvas: Canvas, paint: Paint) {
        // 去除锯齿
        paint.isAntiAlias = true
        // 设置颜色
        paint.color = Color.parseColor("#123456")
        // 绘制圆角矩形
        canvas.drawOval(100f, 1000f, 300f, 1100f, paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f
        canvas.drawOval(400f, 1010f, 600f, 1090f, paint)
    }

    fun drawArc(canvas: Canvas, paint: Paint) {
        // 去除锯齿
        paint.isAntiAlias = true
        // 设置颜色
        paint.color = Color.parseColor("#123456")

        val rel = RectF(100f, 1300f, 300f, 1500f)
        // 实心圆弧
        canvas.drawArc(rel, 0f, 270f, false, paint)

//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 20f
//        canvas.drawOval(400f, 1010f, 600f, 1090f, paint)
    }

}
