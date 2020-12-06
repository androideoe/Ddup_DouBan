package com.gxp.ddup_douban.ui.datasource

import androidx.paging.PageKeyedDataSource
import com.gxp.ddup_douban.bean.TopicBean
import com.gxp.ddup_douban.http.Constants
import com.gxp.ddup_douban.repository.TopicRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 * Created by ddup on 2020/11/16.
 */

class TopicDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: TopicRepository
) : PageKeyedDataSource<String, TopicBean.IssueListBean.ItemListBean>() {


    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, TopicBean.IssueListBean.ItemListBean>
    ) {
        coroutineScope.launch {
            try {
                val response = repository.getTopicData(Constants.REQUEST_BASE_URL)
                val (date, mList) = getVideoData(response)
                callback.onResult(mList, null, date)
            } catch (e: Exception) {
                print(e.message)
            }
        }

    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, TopicBean.IssueListBean.ItemListBean>
    ) {
        coroutineScope.launch {
            try {
                val response = repository.getMoreTopicData(Constants.REQUEST_BASE_URL, params.key)
                val (date, mList) = getVideoData(response)
                callback.onResult(mList, date)
            } catch (e: Exception) {
                print(e.message)
            }
        }

    }


    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, TopicBean.IssueListBean.ItemListBean>
    ) {

    }

    /**
     * 将请求数据转换成需要的数据
     */
    private fun getVideoData(response: TopicBean?): Pair<String?, ArrayList<TopicBean.IssueListBean.ItemListBean>> {
        val nextPageUrl = response?.nextPageUrl
        val date = nextPageUrl?.let { getKey(it) }
        val mList = ArrayList<TopicBean.IssueListBean.ItemListBean>()
        response?.issueList
            ?.flatMap { it.itemList!! }
            ?.filter { it.type.equals("video") }
            ?.forEach { mList.add(it) }
        return Pair(date, mList)
    }

    /**
     * 从url中获取下次请求的date
     */
    private fun getKey(url: String): String {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(url)
        val data = m.replaceAll("")
            .subSequence(1, m.replaceAll("").length - 1).toString()
        return data
    }
}