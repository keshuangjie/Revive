package com.jimmy.revive.sample.animate.transition

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View

import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity
import com.jimmy.revive.sample.animate.VideoLayerView
import kotlinx.android.synthetic.main.test_animate_activity.*

/**
 * 属性动画
 * Created by Jimmy on 2017/12/28.
 */
class TestAnimateActivity : ReviveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_animate_activity)

        btn_start.setOnClickListener {
            progress()
        }
        btn_stop.setOnClickListener { }
        layer.setListener(object : VideoLayerView.OnVideoClickListener {
            override fun onClick() {
                Log.e("TestAnimateActivity", "onClick")
            }

            override fun onDoubleClick(event: MotionEvent) {
                Log.e("TestAnimateActivity", "onDoubleClick event: " + event.x + "/" + event.y)
            }
        })
    }

    private fun start() {
        //沿x轴放大
        val scaleXAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0f)
        //沿y轴放大
        val scaleYAnimator = ObjectAnimator.ofFloat(iv, "scaleY", 1f, 0f)
        //移动
        //        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f, 0f);
        //透明动画
        val animator = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0f)
        val set = AnimatorSet()
        //同时沿X,Y轴放大，且改变透明度，然后移动
        set.play(scaleXAnimator).with(scaleYAnimator).with(animator)
        //都设置3s，也可以为每个单独设置
        set.duration = 300
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                iv.setImageResource(R.drawable.asgard__video_icon_praise_selected)
                scale()
            }
        })
        set.start()
    }

    private fun scale() {
        //沿x轴放大
        val scaleXAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 1f)
        //沿y轴放大
        val scaleYAnimator = ObjectAnimator.ofFloat(iv, "scaleY", 0f, 1f)
        //移动
        //        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f, 0f);
        //透明动画
        val animator = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f)
        val set = AnimatorSet()
        //同时沿X,Y轴放大，且改变透明度，然后移动
        set.play(scaleXAnimator).with(scaleYAnimator).with(animator)
        //都设置3s，也可以为每个单独设置
        set.duration = 300
        set.start()
    }

    private fun progress() {
        //沿x轴放大
        val scaleXAnimator = ObjectAnimator.ofFloat(iv_progress, "scaleX", 0f, 1f)
        val scaleYAnimator = ObjectAnimator.ofFloat(iv, "scaleY", 1f, 0.6f)
        scaleXAnimator.repeatCount = 2000
        scaleYAnimator.repeatCount = 2000
        //移动
        //        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f, 0f);
        //透明动画
        val animator = ObjectAnimator.ofFloat(iv_progress, "alpha", 1f, 0.6f)
        animator.repeatCount = 2000
        val set = AnimatorSet()
        //同时沿X,Y轴放大，且改变透明度，然后移动
        set.play(scaleXAnimator).with(scaleYAnimator).with(animator)
        //都设置3s，也可以为每个单独设置
        set.duration = 1000
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                iv_progress.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {
                iv_progress.visibility = View.GONE
            }
        })
        set.start()
    }

}
