package com.rkm.tdd.shuowen.di

import com.rkm.tdd.shuowen.fragment.BookImageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BookImageActivityBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeBookImageFragment(): BookImageFragment
}