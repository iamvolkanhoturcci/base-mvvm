package com.volkanhotur.basemvvm.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.volkanhotur.basemvvm.di.builder.BaseActivityBuilder
import com.volkanhotur.basemvvm.di.builder.BaseFragmentBuilder
import com.volkanhotur.basemvvm.extension.defaultSharedPreference
import com.volkanhotur.basemvvm.utils.helper.SharedHelper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * @author volkanhotur
 */

@Module(includes = [ApiModule::class, BaseActivityBuilder::class, BaseFragmentBuilder::class, BaseViewModelModule::class])
class AppModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.defaultSharedPreference(), Context.MODE_PRIVATE)
    }

    @Singleton @Provides
    fun provideSharedPrefHelper(builder: GsonBuilder, sharedPreferences: SharedPreferences): SharedHelper {
        return SharedHelper(sharedPreferences, builder.create())
    }
}