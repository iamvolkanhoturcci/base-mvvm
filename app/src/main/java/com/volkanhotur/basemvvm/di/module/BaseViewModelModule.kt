package com.volkanhotur.basemvvm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.volkanhotur.basemvvm.di.qualifier.ViewModelKey
import com.volkanhotur.basemvvm.ui.screen.splash.SplashViewModel
import com.core.viewmodel.factory.ViewModelFactory
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
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(viewModel: SplashViewModel?): ViewModel?

}