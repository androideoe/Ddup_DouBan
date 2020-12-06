package com.gxp.ddup_douban.base

import android.app.Application

/**
 * Created by ddup on 2020/10/25.
 *
 * MVVM + Jetpack + kotlin 练手项目
 */
class DouBanApp : Application() {

    companion object {
        lateinit var instance: DouBanApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
    }
}