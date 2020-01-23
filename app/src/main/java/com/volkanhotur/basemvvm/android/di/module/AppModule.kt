package com.volkanhotur.basemvvm.android.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.GsonBuilder
import com.volkanhotur.basemvvm.android.di.builder.BaseActivityBuilder
import com.volkanhotur.basemvvm.android.di.builder.BaseFragmentBuilder
import com.volkanhotur.basemvvm.android.utils.helper.SharedHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.IOException
import java.security.GeneralSecurityException
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
        try {
            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            return EncryptedSharedPreferences.create(
                    "com.volkanhotur.basemvvm.android.shared.pref",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return context.getSharedPreferences("com.volkanhotur.basemvvm.android.shared.pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPrefHelper(builder: GsonBuilder, sharedPreferences: SharedPreferences?): SharedHelper {
        return SharedHelper(sharedPreferences, builder.create())
    }
}