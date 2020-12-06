package com.gxp.ddup_douban.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gxp.ddup_douban.bean.TopicBean
import com.gxp.ddup_douban.repository.TopicRepository
import com.gxp.ddup_douban.ui.datasource.TopicDataSourceFactory

/**
 * Created by ddup on 2020/11/13.
 * 首页数据ViewModel
 */
class TopicViewModel : ViewModel() {

    private val TAG: String = TopicViewModel::class.java.simpleName.toString()

    private val repository = TopicRepository()

    private val factory = TopicDataSourceFactory(viewModelScope, repository)

//    private val dataSource: DataSource<String, TopicBean.IssueListBean.ItemListBean> =
//        factory.create()


//    fun getTopicData() {
//        viewModelScope.launch {
//
//            val apiService =
//                RetrofitClient.getInstance(context, baseUrl).create(ApiService::class.java)
//            if (apiService != null) {
//                val topicBean = apiService.getTopicBean();
//                topicLiveData.value = topicBean
//            }
//
//        }

//        launch(
//            {
//                topicLiveData.value = repository.getTopicData(Constants.REQUEST_BASE_URL)
//            },
//            {
//                Log.e(TAG, "getTopicBean error->" + it.message)
//            },
//            {
//                Log.e(TAG, "getTopicBean onComplete...")
//            }
//        )


//    }


    val topicPagedList: LiveData<PagedList<TopicBean.IssueListBean.ItemListBean>> =
        LivePagedListBuilder<String, TopicBean.IssueListBean.ItemListBean>(
            factory, PagedList.Config.Builder()
                .setPageSize(10)                         // 配置分页加载的数量
                .setEnablePlaceholders(false)     // 配置是否启动PlaceHolders
                .setInitialLoadSizeHint(30)              // 初始化加载的数量
                .build()
        ).build()

    fun invalidateDataSource() {
        factory.liveDataSource.value?.invalidate()
    }
}