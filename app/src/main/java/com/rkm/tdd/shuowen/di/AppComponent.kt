package com.rkm.tdd.shuowen.di

import com.rkm.tdd.shuowen.ShuowenApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    UIModule::class
])
interface AppComponent : AndroidInjector<ShuowenApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: ShuowenApp): Builder
        fun build(): AppComponent
    }

    override fun inject(app: ShuowenApp)
}
