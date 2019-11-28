package com.volkanhotur.basemvvm.android.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.core.viewmodel.factory.ViewModelFactory;
import com.volkanhotur.basemvvm.android.di.qualifier.ViewModelKey;
import com.volkanhotur.basemvvm.android.ui.screen.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author volkanhotur
 */

@Module
public abstract class BaseViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel mainViewModel(MainViewModel viewModel);
}
