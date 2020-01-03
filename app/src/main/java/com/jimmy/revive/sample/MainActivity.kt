package com.jimmy.revive.sample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity
import com.jimmy.revive.sample.animate.transition.TestAnimateActivity
import com.jimmy.revive.sample.bitmap.BitmapSizeActivity
import com.jimmy.revive.sample.canvas.CanvasActivity
import com.jimmy.revive.sample.chart.ChartActivity
import com.jimmy.revive.sample.lifecycle.TestLifecycleActivity
import com.jimmy.revive.sample.nestedlistview.GoNetScrollViewPagerActivity
import com.jimmy.revive.sample.notify.PracticeCountNotifyManager
import com.jimmy.revive.sample.notify.PracticeCountNotifyReceiver
import kotlinx.android.synthetic.main.test_main_activity.*

class MainActivity : ReviveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_main_activity)
        val list = initData()
        list.map { initItemView(it) }.forEach { tagsView.addView(it) }
        createNotificationChannel()
    }

    private fun initData(): ArrayList<ItemData> {
        val list = ArrayList<ItemData>()
        list.add(ItemData("test", View.OnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }))
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
        list.add(ItemData("内嵌listView", View.OnClickListener {
            val intent = Intent(this, GoNetScrollViewPagerActivity::class.java)
            startActivity(intent)
        }))
        list.add(ItemData("notify", View.OnClickListener {
            Toast.makeText(this, "notify click", Toast.LENGTH_SHORT).show()
            PracticeCountNotifyManager.triggerNextNotify()
        }))
        list.add(ItemData("bitmap", View.OnClickListener {
            val intent = Intent(this, BitmapSizeActivity::class.java)
            startActivity(intent)
        }))

        return list
    }

    /**
     * 针对8.0 创建推送专有的channel_id
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(PracticeCountNotifyReceiver.NOTIFY_ID, "消息通知",
                    NotificationManager.IMPORTANCE_DEFAULT)
            val manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager?.createNotificationChannel(channel)
        }
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
