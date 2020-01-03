package com.jimmy.revive.lib.framework.view

import android.content.Context
import android.graphics.*
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View
import com.jimmy.revive.lib.R

import com.jimmy.revive.lib.framework.util.DimenUtils

class CircleProgress @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var paint: Paint = Paint()

    private val bounds = RectF()
    private val oval = RectF()

    // 进度条的背景颜色
    @ColorInt
    private var bgColor = Color.parseColor("#FFE9FBFF")
    // 进度颜色
    @ColorInt
    private var startProgressColor = Color.parseColor("#FF00E0E5")
    @ColorInt
    private var endProgressColor = Color.parseColor("#FF0086FA")
    //进度条渐变效果
    private var sweepGradient: SweepGradient? = null
    // 进度条宽度，单位dp
    private var progressWidth = DimenUtils.dp2px(10f)

    private var realProgress = 0f

    init {

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.CircleProgress, 0, 0)
        try {
            val bgColor = a.getColor(R.styleable.CircleProgress_bgColor, -1)
            val startProgressColor = a.getColor(R.styleable.CircleProgress_startProgressColor, -1)
            val endProgressColor = a.getColor(R.styleable.CircleProgress_endProgressColor, -1)
            val progressWidth = a.getDimension(R.styleable.CircleProgress_progressWidth, -1f)
            if (bgColor != -1 ) {
                this.bgColor = bgColor
            }
            if (startProgressColor != -1) {
                this.startProgressColor = startProgressColor
            }
            if (endProgressColor != -1) {
                this.endProgressColor = endProgressColor
            }
            if (progressWidth != -1f) {
                this.progressWidth = progressWidth.toInt()
            }
        } finally {
            a.recycle()
        }

        initPaint()
    }

    private fun initPaint() {
        paint.isAntiAlias = true
        paint.strokeWidth = progressWidth.toFloat()
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val centre = ((right - left) / 2).toFloat()
        val radius = centre - 1.0f * progressWidth
        bounds.set(centre - radius, centre - radius, centre + radius, centre + radius)
        oval.set(bounds)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = bgColor
        // 绘制背景
        canvas.drawArc(oval, 0f, 360f, false, paint)

        if (sweepGradient == null) {
            sweepGradient = SweepGradient(bounds.width() * 1f / 2, bounds.height() * 1f / 2,
                    startProgressColor, endProgressColor)
            //旋转sweepGradient
            val matrix = Matrix()
            matrix.setRotate(270f, measuredWidth.toFloat() / 2, measuredHeight.toFloat() / 2)
            sweepGradient?.setLocalMatrix(matrix)
        }
        paint.shader = sweepGradient

        // 绘制进度
        canvas.drawArc(oval, 270f, realProgress, false, paint)
        paint.shader = null
    }

    /**
     * 更新进度
     */
    fun updateProgress(progress: Float) {
        val circleProgress = if (progress < 0 || progress.toDouble() == 0.0) {
            0f
        } else {
            progress * 1.0f / 100 * 360
        }
        realProgress = circleProgress
        invalidate()
    }

}