package com.rkm.tdd.shuowen

import com.rkm.tdd.shuowen.di.DaggerAppComponent
import com.rkm.tdd.shuowen.di.applyAutoInjector
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class ShuowenApp : DaggerApplication() {

    @Inject lateinit var appLifecycleCallback: AppLifecycleCallback

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
        appLifecycleCallback.onCreate(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        appLifecycleCallback.onTerminate(this)
    }
}
