package com.gxp.ddup_douban.ui.fragment

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gxp.ddup_douban.R
import com.gxp.ddup_douban.base.BaseFragment
import com.gxp.ddup_douban.model.TopicViewModel
import com.gxp.ddup_douban.ui.adapter.TopicAdapter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by ddup on 2020/10/25.
 *
 * 首页
 */
class HomeFragment : BaseFragment() {

    private val TAG: String = HomeFragment::class.java.simpleName.toString()

    private var mIsRefresh: Boolean = false

    private lateinit var homeViewModel: TopicViewModel

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        val topicAdapter = TopicAdapter()
        rv_home.adapter = topicAdapter
        rv_home.layoutManager = LinearLayoutManager(context)
        homeViewModel = ViewModelProvider(this).get(TopicViewModel::class.java)

        homeViewModel.topicPagedList.observe(
            this, Observer {
                if (mIsRefresh) {
                    mIsRefresh = false;
                    refresh_layout_home.isRefreshing = false;
                }
                topicAdapter.submitList(it)
                Log.e(TAG, it.size.toString());
            })

          refresh_layout_home.setOnRefreshListener {
              if (!mIsRefresh) {
                  mIsRefresh = true
                  homeViewModel.invalidateDataSource()
              }

          }


    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}