package com.jimmy.revive.sample.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 路线趋势图拖动控件
 *
 * Created by Jimmy on 2018/8/9.
 */
class ChartControlView(context: Context, attr: AttributeSet) : View(context, attr) {

    /**
     * 是否在触摸范围的偏差值，越大越容易触发
     */
    private val touchOffset = 120
    // 横向padding
    private val paddingHor = 30f

    // 起始位置
    private var startX: Float = 0f
    // 终点位置
    private var endX: Float = 0f

    // 起始点是否处于拖动状态
    private var isStartDrag = false
    // 终点是否处于拖动状态
    private var isEndDrag = false
    // 手指按下时的X坐标
    private var downEventX = 0f
    // 拖动过程中的变量值
    private var dragDeltaX = 0f

    private val paint = Paint()
    private val paintSize = 3f

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = paintSize
    }

    private var onSlideProgress: OnProgressListener? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var consumed = false

        if (event.action == MotionEvent.ACTION_DOWN) {
            downEventX = event.x

            if (isEventHolder(downEventX)) {
                consumed = true
            }

        } else if (event.action == MotionEvent.ACTION_MOVE) {
            if (isInDrag()) {
                dragDeltaX = event.x - downEventX
                downEventX = event.x
                consumed = true
                parent.requestDisallowInterceptTouchEvent(true)
                processDragChange()
            } else {
                parent.requestDisallowInterceptTouchEvent(false)
                consumed = false
            }

        } else if (event.action == MotionEvent.ACTION_UP) {
            consumed = false

            if (isInDrag()) {
                // 根据比例自动归位到最近的点
            }

            isStartDrag = false
            isEndDrag = false

        }
        if (isInDrag()) {
            invalidate()
        }

        return consumed
    }

    /**
     * 是否出发拖动事件
     */
    private fun isEventHolder(eventX: Float): Boolean {
        val offsetStartX = Math.abs(eventX - startX)
        if (offsetStartX < touchOffset) {
            isStartDrag = true
        }
        val offsetEndX = Math.abs(eventX - endX)
        if (offsetEndX < touchOffset) {
            isEndDrag = true
        }
        if (isStartDrag && isEndDrag) {
            if (offsetStartX > offsetEndX) {
                isStartDrag = false
                isEndDrag = true
            } else {
                isStartDrag = true
                isEndDrag = false
            }
        }
        return isInDrag()
    }

    /**
     * 是否正在触摸中
     */
    private fun isInDrag(): Boolean {
        return isStartDrag || isEndDrag
    }

    private fun processDragChange() {
        if (isStartDrag) {
            startX += dragDeltaX
            startX = Math.max(getInitStart(), startX)
            startX = Math.min(startX, endX-1)
        } else if (isEndDrag) {
            endX += dragDeltaX
            endX = Math.max(startX+1, endX)
            endX = Math.min(endX, getInitEnd())
        }

        onSlideProgress?.let {
            val realWidth = getRealWidth()
            it.onChange((startX - paddingHor) / realWidth, (endX - paddingHor) / realWidth)
        }
    }

    /**
     * 默认初始位置
     */
    private fun getInitStart(): Float {
        return paddingHor
    }

    /**
     * 默认终点位置
     */
    private fun getInitEnd(): Float {
        return width - paddingHor
    }

    /**
     * 实际可移动宽度
     */
    private fun getRealWidth(): Float {
        return width - paddingHor * 2
    }

    /**
     * 自动归位到最近的刻度位置
     */
    fun autoReset(startProgress: Float, endProgress: Float) {
        startX = startProgress * getRealWidth() + paddingHor
        endX = endProgress * getRealWidth() + paddingHor
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        realDraw(canvas)
    }

    /**
     * 绘制带透明背景
     */
    private fun realDraw(canvas: Canvas) {
        if (startX == 0f) {
            startX = getInitStart()
        }

        if (endX == 0f) {
            endX = getInitEnd()
        }

        paint.color = Color.parseColor("#0DFFDD33")
        // 绘制矩形
        canvas.drawRect(startX, 0f, endX, height.toFloat(), paint)

        paint.color = Color.parseColor("#FF2B00")
        // 绘制左边线
        canvas.drawLine(startX, 0f, startX + paintSize, height.toFloat(), paint)

        canvas.drawCircle(startX, 7.5f, 7.5f, paint)

        // 绘制右边线
        canvas.drawLine(endX - paintSize, 0f, endX, height.toFloat(), paint)
        canvas.drawCircle(endX - paintSize/2, 7.5f, 7.5f, paint)
    }

    interface OnProgressListener {
        /**
         * @param startProgress 左边百分百
         * @param endProgress 右边百分百
         */
        fun onChange(startProgress: Float, endProgress: Float)
    }

}