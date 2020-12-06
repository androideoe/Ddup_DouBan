package com.gxp.ddup_douban.ui.fragment

import com.gxp.ddup_douban.R
import com.gxp.ddup_douban.base.BaseFragment

/**
 * Created by ddup on 2020/10/25.
 *
 * 搜索
 */
class SearchFragment : BaseFragment() {
    override fun getContentLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}