package com.rkm.tdd.shuowen.di

import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VolumeListViewModel::class)
    abstract fun bindVolumeListViewModel(viewModel: VolumeListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    abstract fun bindImageListViewModel(viewModel: ImageListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WordDetailListViewModel::class)
    abstract fun bindWordDetailListViewModel(viewModel: WordDetailListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WordDetailViewModel::class)
    abstract fun bindWordDetailViewModel(viewModel: WordDetailViewModel): ViewModel
}
