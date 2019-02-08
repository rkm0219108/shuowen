package com.rkm.tdd.shuowen.di

import androidx.lifecycle.ViewModelProvider
import com.rkm.tdd.shuowen.activity.*
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
    internal abstract fun contributeBookImageListActivity(): BookImagesActivity

    @ContributesAndroidInjector(modules = [WordDetailActivityBuildersModule::class])
    internal abstract fun contributeWordDetailActivity(): WordDetailActivity

    @ContributesAndroidInjector
    internal abstract fun contributeWordEditActivity(): WordEditActivity

    @ContributesAndroidInjector
    internal abstract fun contributeBookImageActivity(): BookImageActivity
}
