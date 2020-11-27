package com.rb.billKotlin

import android.app.Application

/**
 * @author: changZePeng
 * @date: 2020/8/31
 */
class MyApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycle())
    }

}