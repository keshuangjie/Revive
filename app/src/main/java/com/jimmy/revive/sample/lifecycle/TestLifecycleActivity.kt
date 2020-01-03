package com.jimmy.revive.sample.lifecycle

import android.os.Bundle
import androidx.core.app.Fragment
import androidx.core.app.FragmentPagerAdapter
import android.view.View
import com.jimmy.revive.R
import com.jimmy.revive.lib.base.ReviveActivity
import kotlinx.android.synthetic.main.test_lifecycle_activity.*

/**
 * Created by Jimmy on 2017/12/21.
 */
class TestLifecycleActivity : TestLifecycleBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_lifecycle_activity)
        initViewPager()
    }

    fun initViewPager() {
        val dataList = mutableListOf<Fragment>()
        dataList.add(newInstance(0))
        dataList.add(newInstance(1))
        viewPager.adapter = MyAdapter(dataList)
        viewPager.currentItem = 0
    }

    fun onClickHandler(view: View) {
        when (view.id) {
            R.id.tv_page1 -> viewPager.currentItem = 0
            R.id.tv_page2 -> viewPager.currentItem = 1
        }
    }

    inner class MyAdapter(val dataList: List<Fragment>?) : FragmentPagerAdapter(supportFragmentManager) {

        override fun getCount(): Int {
            return dataList?.size ?: 0
        }

        override fun getItem(position: Int): Fragment? {
            return dataList?.get(position) ?: null
        }

    }

}