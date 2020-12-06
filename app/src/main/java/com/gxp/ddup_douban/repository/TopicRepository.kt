package com.gxp.ddup_douban.repository

import android.content.Context
import com.gxp.ddup_douban.bean.TopicBean
import com.gxp.ddup_douban.http.ApiService
import com.gxp.ddup_douban.http.RetrofitClient

/**
 * Created by ddup on 2020/11/14.
 *
 */

class TopicRepository {

    /**
     * 获取首页数据
     */
    suspend fun getTopicData(baseUrl: String): TopicBean? {
        return RetrofitClient.getInstance(baseUrl).create(ApiService::class.java)
            ?.getTopicBean()
    }

    /**
     * 获取首页之后的数据
     */
    suspend fun getMoreTopicData(baseUrl: String, data: String): TopicBean? {
        return RetrofitClient.getInstance(baseUrl).create(ApiService::class.java)
            ?.getMoreTopicBean(data, "2")
    }


}
