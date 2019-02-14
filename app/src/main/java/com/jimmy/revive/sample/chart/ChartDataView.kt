package com.jimmy.revive.sample.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * Created by Jimmy on 2018/8/9.
 */
class ChartDataView(context: Context, attr: AttributeSet) : View(context, attr) {
    private val paintSize = 3f
    // 横坐标线数量
    private val horLineCount = 7

    private val data = mutableListOf<Int>()

    private var paint: Paint = Paint()

    init {
        paint.color = Color.parseColor("#eeeeee")
        paint.strokeWidth = paintSize
        paint.isAntiAlias = true
        data.add(0, 100)
        for (i in 0 until 50) {
            data.add(Random().nextInt(100))
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawX(canvas)
        drawY(canvas)
        drawData(canvas)
    }

    private fun drawY(canvas: Canvas) {
        paint.color = Color.parseColor("#eeeeee")
        paint.strokeWidth = paintSize
        canvas.drawLine(0f, 0f, paintSize, height.toFloat(), paint)
    }

    private fun drawX(canvas: Canvas) {
        paint.color = Color.parseColor("#eeeeee")
        paint.strokeWidth = paintSize
        val avrHeight = (height - paintSize) * 1f / (horLineCount - 1)
        for (i in 0 until horLineCount) {
            canvas.drawLine(paintSize, avrHeight * i, width.toFloat() - paintSize,
                    avrHeight * i + paintSize, paint)
        }
    }

    private fun drawData(canvas: Canvas) {
        val max = 100
        val size = data.size
        val perX = width.toFloat() / size
        val perY = height.toFloat() / max
        val points = mutableListOf<PointF>()
        for ((index, i) in data.withIndex()) {
            val point = PointF()
            point.x = perX * index
            point.y = i * perY
            points.add(point)
        }
        paint.color = Color.parseColor("#FF2B00")
        paint.strokeWidth = 4f
        for ((index, p) in points.withIndex()) {
            if (index == size - 1) {
                return
            }
            val p2 = points[index + 1]
            canvas.drawLine(p.x, p.y, p2.x, p2.y, paint)
        }
    }

}