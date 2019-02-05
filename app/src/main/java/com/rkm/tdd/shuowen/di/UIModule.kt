package com.rkm.tdd.shuowen.di

import androidx.lifecycle.ViewModelProvider
import com.rkm.tdd.shuowen.activity.BookVolumeListActivity
import com.rkm.tdd.shuowen.activity.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
internal abstract class UIModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeBookVolumeListActivity(): BookVolumeListActivity
}
