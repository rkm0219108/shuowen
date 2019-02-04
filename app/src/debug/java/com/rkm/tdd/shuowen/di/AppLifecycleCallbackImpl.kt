package com.rkm.tdd.shuowen.di

import android.app.Application
import com.rkm.tdd.shuowen.AppLifecycleCallback
import timber.log.Timber

class AppLifecycleCallbackImpl : AppLifecycleCallback {
    override fun onCreate(app: Application) {
        Timber.plant(Timber.DebugTree())
    }

    override fun onTerminate(app: Application) {}
}
