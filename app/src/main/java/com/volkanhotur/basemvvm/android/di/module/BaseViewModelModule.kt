package com.volkanhotur.basemvvm.android.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.viewmodel.factory.ViewModelFactory
import com.volkanhotur.basemvvm.android.di.qualifier.ViewModelKey
import com.volkanhotur.basemvvm.android.ui.screen.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author volkanhotur
 */
@Module
abstract class BaseViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel?): ViewModel?
}