package com.rkm.tdd.shuowen.di

import androidx.lifecycle.ViewModelProvider
import com.rkm.tdd.shuowen.activity.BookImageListActivity
import com.rkm.tdd.shuowen.activity.BookVolumeListActivity
import com.rkm.tdd.shuowen.activity.MainActivity
import com.rkm.tdd.shuowen.activity.WordDetailActivity
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

    @ContributesAndroidInjector
    internal abstract fun contributeBookImageListActivity(): BookImageListActivity

    @ContributesAndroidInjector(modules = [WordDetailActivityBuildersModule::class])
    internal abstract fun contributeWordDetailActivity(): WordDetailActivity
}
