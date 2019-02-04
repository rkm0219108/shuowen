package com.rkm.tdd.shuowen.di

import com.rkm.tdd.shuowen.AppLifecycleCallback
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppLifecycleCallback(): AppLifecycleCallback = AppLifecycleCallbackImpl()

}
