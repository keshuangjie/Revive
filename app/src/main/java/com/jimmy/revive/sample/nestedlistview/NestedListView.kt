package com.jimmy.revive.sample.nestedlistview

import android.content.Context
import android.os.Build
import android.support.v4.view.NestedScrollingChild2
import android.support.v4.view.NestedScrollingChildHelper
import android.util.AttributeSet
import android.widget.ListView

/**
 * Created by Jimmy on 2019/7/4.
 */
class NestedListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs), NestedScrollingChild2 {

    private var mScrollingChildHelper: NestedScrollingChildHelper = NestedScrollingChildHelper(this)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            isNestedScrollingEnabled = true
        }
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setNestedScrollingEnabled(enabled)
        } else {
            mScrollingChildHelper.isNestedScrollingEnabled = enabled
        }
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return if (Build.VERSION.SDK_INT >= 21) {
            super.isNestedScrollingEnabled()
        } else mScrollingChildHelper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return if (Build.VERSION.SDK_INT >= 21) {
            super.startNestedScroll(axes)
        } else mScrollingChildHelper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        if (Build.VERSION.SDK_INT >= 21) {
            super.stopNestedScroll()
            return
        }
        mScrollingChildHelper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return if (Build.VERSION.SDK_INT >= 21) {
            super.hasNestedScrollingParent()
        } else mScrollingChildHelper.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int,
                                      dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        return if (Build.VERSION.SDK_INT >= 21) {
            super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
        } else mScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        return if (Build.VERSION.SDK_INT >= 21) {
            super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
        } else mScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return if (Build.VERSION.SDK_INT >= 21) {
            super.dispatchNestedFling(velocityX, velocityY, consumed)
        } else mScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return if (Build.VERSION.SDK_INT >= 21) {
            super.dispatchNestedPreFling(velocityX, velocityY)
        } else mScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun startNestedScroll(i: Int, i1: Int): Boolean {
        return mScrollingChildHelper.startNestedScroll(i, i1)
    }

    override fun stopNestedScroll(i: Int) {
        mScrollingChildHelper.stopNestedScroll(i)
    }

    override fun hasNestedScrollingParent(i: Int): Boolean {
        return mScrollingChildHelper.hasNestedScrollingParent(i)
    }

    override fun dispatchNestedScroll(i: Int, i1: Int, i2: Int, i3: Int, ints: IntArray?, i4: Int): Boolean {
        return mScrollingChildHelper.dispatchNestedScroll(i, i1, i2, i3, ints, i4)
    }

    override fun dispatchNestedPreScroll(i: Int, i1: Int, ints: IntArray?, ints1: IntArray?, i2: Int): Boolean {
        return mScrollingChildHelper.dispatchNestedPreScroll(i, i1, ints, ints1, i2)
    }
}