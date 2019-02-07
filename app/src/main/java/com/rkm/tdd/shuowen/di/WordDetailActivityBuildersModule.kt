package com.rkm.tdd.shuowen.di

import com.rkm.tdd.shuowen.fragment.WordDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WordDetailActivityBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeWordDetailFragment(): WordDetailFragment
}
