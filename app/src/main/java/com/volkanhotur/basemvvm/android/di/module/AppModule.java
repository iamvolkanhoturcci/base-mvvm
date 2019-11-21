package com.volkanhotur.basemvvm.android.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.gson.GsonBuilder;
import com.volkanhotur.basemvvm.android.di.builder.ActivityBuilder;
import com.volkanhotur.basemvvm.android.di.builder.FragmentBuilder;
import com.volkanhotur.basemvvm.android.utils.helper.SharedHelper;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = {ApiModule.class, ActivityBuilder.class, FragmentBuilder.class, ViewModelModule.class})
public class AppModule {

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            return EncryptedSharedPreferences.create(
                    "com.volkanhotur.basemvvm.android.shared.pref",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        return context.getSharedPreferences("com.volkanhotur.basemvvm.android.shared.pref", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedHelper provideSharedPrefHelper(GsonBuilder builder, SharedPreferences sharedPreferences) {
        return new SharedHelper(sharedPreferences, builder.create());
    }
}
