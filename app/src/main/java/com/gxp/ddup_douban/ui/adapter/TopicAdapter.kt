package com.gxp.ddup_douban.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gxp.ddup_douban.BR
import com.gxp.ddup_douban.R
import com.gxp.ddup_douban.bean.TopicBean
import com.gxp.ddup_douban.ui.viewholder.TopicViewHolder

/**
 * Created by ddup on 2020/11/15.
 * 首页数据adapter
 */
class TopicAdapter :
    PagedListAdapter<TopicBean.IssueListBean.ItemListBean, TopicViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_topic,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val binding: ViewDataBinding = holder.dataBinding
        binding.setVariable(BR.videoDataBean, getItem(position))
        binding.executePendingBindings()

    }

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<TopicBean.IssueListBean.ItemListBean>() {
                override fun areItemsTheSame(
                    oldItem: TopicBean.IssueListBean.ItemListBean,
                    newItem: TopicBean.IssueListBean.ItemListBean
                ): Boolean =
                    oldItem.data == newItem.data

                override fun areContentsTheSame(
                    oldItem: TopicBean.IssueListBean.ItemListBean,
                    newItem: TopicBean.IssueListBean.ItemListBean
                ): Boolean =
                    oldItem.data?.id == newItem.data?.id
            }

    }

}
