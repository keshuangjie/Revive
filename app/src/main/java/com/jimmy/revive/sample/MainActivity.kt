package com.jimmy.revive.sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity
import com.jimmy.revive.sample.animate.transition.TestAnimateActivity
import com.jimmy.revive.sample.canvas.CanvasActivity
import com.jimmy.revive.sample.chart.ChartActivity
import com.jimmy.revive.sample.lifecycle.TestLifecycleActivity
import kotlinx.android.synthetic.main.test_main_activity.*

class MainActivity : ReviveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_main_activity)
        val list = initData()
        list.map { initItemView(it) }.forEach { tagsView.addView(it) }
    }

    private fun initData(): ArrayList<ItemData> {
        val list = ArrayList<ItemData>()
        list.add(ItemData("Activity生命周期", View.OnClickListener {
            startActivity(Intent(this, TestLifecycleActivity::class.java))
        }))

        list.add(ItemData("动画", View.OnClickListener {
            val intent = Intent(this, TestAnimateActivity::class.java)
            startActivity(intent)
        }))
        list.add(ItemData("绘图", View.OnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)
            startActivity(intent)
        }))
        list.add(ItemData("表格", View.OnClickListener {
            val intent = Intent(this, ChartActivity::class.java)
            startActivity(intent)
        }))
        return list
    }

    private fun initItemView(itemData: ItemData): View {
        val childView = LayoutInflater.from(this).inflate(R.layout.test_main_item, null)
        val textView = childView.findViewById<TextView>(R.id.tv)
        textView.text = itemData.text
        childView.setOnClickListener(itemData.click)
        return childView
    }

    data class ItemData(val text: String, val click: View.OnClickListener)
}
