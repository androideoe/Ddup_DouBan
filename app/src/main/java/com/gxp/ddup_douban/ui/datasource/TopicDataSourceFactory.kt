package com.gxp.ddup_douban.ui.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gxp.ddup_douban.bean.TopicBean
import com.gxp.ddup_douban.repository.TopicRepository
import kotlinx.coroutines.CoroutineScope


/**
 * Created by ddup on 2020/11/16.
 */

class TopicDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: TopicRepository
) : DataSource.Factory<String, TopicBean.IssueListBean.ItemListBean>() {

    val liveDataSource: MutableLiveData<TopicDataSource> =
        MutableLiveData<TopicDataSource>()

    override fun create(): DataSource<String, TopicBean.IssueListBean.ItemListBean> {
        val dataSource = TopicDataSource(coroutineScope, repository)
        liveDataSource.postValue(dataSource)
        return dataSource

    }

}