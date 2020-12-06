package com.gxp.ddup_douban.http

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ddup on 2020/11/12.
 *
 * retrofit网络请求工具类
 */


class RetrofitClient private constructor(baseUrl: String) {
    val TAG: String = "RetrofitClient"
//    val mContext: Context = context
    val url = baseUrl
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    var DEFAULT_TIMEOUT: Long = 30


    init {

        // 日志显示级别
        val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
        // 新建log拦截器
        val httpLoggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.d(TAG, message)
            })
        httpLoggingInterceptor.level = level

        // okhttpclient创建
        okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        // retrofit创建
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()

    }

    companion object {
        @Volatile
        var instance: RetrofitClient? = null
        fun getInstance(baseUrl: String): RetrofitClient {
            if (instance == null) {
                synchronized(RetrofitClient::class) {
                    if (instance == null) {
                        instance = RetrofitClient(baseUrl)
                    }
                }
            }
            return instance!!
        }
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }

}