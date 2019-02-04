package com.rkm.tdd.shuowen.di

import android.app.Application
import com.rkm.tdd.shuowen.AppLifecycleCallback

class AppLifecycleCallbackImpl : AppLifecycleCallback {
    override fun onCreate(app: Application) {}

    override fun onTerminate(app: Application) {}
}
