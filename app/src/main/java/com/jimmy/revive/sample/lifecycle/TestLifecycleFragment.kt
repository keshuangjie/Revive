package com.jimmy.revive.sample.lifecycle

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jimmy.revive.R
import com.jimmy.revive.lib.framework.util.w
import kotlinx.android.synthetic.main.test_lifecycle_fragment.*

/**
 * Created by Jimmy on 2017/12/21.
 */
class TestLifecycleFragment : Fragment() {
    var page: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test_lifecycle_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            page = it.getString("currentPage")
            val textValue = page
            textView.text = "This is $textValue page"
        }
        textView.setOnClickListener {
            this.startActivityForResult(Intent(activity, TestResultActivity::class.java), 100)
        }
        log("onActivityResult textView context " + textView.context::class.java.name)
        log("onActivityResult textViewWithTheme context" + textViewWithTheme.context::class.java.name)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        log("onActivityResult")
    }

    fun log(event: String) {
        w("TestLifecycleFragment $event $page")
    }
}

fun newInstance(page: Int) : Fragment {
    val bundle = Bundle()
    bundle.putString("currentPage", page.toString())
    val fragment = TestLifecycleFragment()
    fragment.arguments = bundle
    return fragment
}