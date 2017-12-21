package com.jimmy.hellokotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jimmy.hellokotlin.lifecycle.TestLifecycleActivity
import kotlinx.android.synthetic.main.test_main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_main_activity)
        val list = initData()
        list.map { initItemView(it) }.forEach { tagsView.addView(it) }
    }

    fun initData(): ArrayList<ItemData> {
        val list = ArrayList<ItemData>()
        list.add(ItemData("Activity lifecycle", View.OnClickListener {
            startActivity(Intent(this, TestLifecycleActivity::class.java))
        }))
        return list
    }

    fun initItemView(itemData: ItemData): View {
        val childView = LayoutInflater.from(this).inflate(R.layout.test_main_item, null)
        val textView = childView.findViewById<TextView>(R.id.tv)
        textView.text = itemData.text
        childView.setOnClickListener(itemData.click)
        return childView
    }

    data class ItemData(val text: String, val click: View.OnClickListener)
}
