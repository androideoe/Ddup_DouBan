package com.gxp.ddup_douban.http

import com.gxp.ddup_douban.bean.TopicBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ddup on 2020/11/13.
 * 网络实现接口类
 */

interface ApiService {

    // 首页数据请求
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    suspend fun getTopicBean(): TopicBean

    // 获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET("v2/feed")
    suspend fun getMoreTopicBean(
        @Query("date") date: String,
        @Query("num") num: String
    ): TopicBean


}