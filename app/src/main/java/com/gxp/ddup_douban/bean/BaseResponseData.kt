package com.gxp.ddup_douban.bean

/**
 * Created by ddup on 2020/11/13.
 * 请求返回结果包装类
 */
data class BaseResponseData<T>(

    val code: Int,
    val msg: String,
    val data: T
)