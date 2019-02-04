package com.rkm.tdd.shuowen

import android.app.Application

interface AppLifecycleCallback {
    fun onCreate(app: Application)
    fun onTerminate(app: Application)
}
