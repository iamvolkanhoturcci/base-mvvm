package com.volkanhotur.basemvvm.android.di.module;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.volkanhotur.basemvvm.BuildConfig;
import com.volkanhotur.basemvvm.android.data.repository.Api;
import com.volkanhotur.basemvvm.android.data.repository.ApiDataFactory;
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author volkanhotur
 */

@Module
public class ApiModule {
    @Singleton
    @Provides
    @Named("BASE_URL")
    String provideBaseUrl() {
        return "https://api.github.com/";
    }

    @Singleton
    @Provides
    GsonBuilder provideGsonBuilder(JsonDeserializer<Date> dateJsonDeserializer) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, dateJsonDeserializer);

        return gsonBuilder;
    }

    @Singleton
    @Provides
    Converter.Factory provideConverterFactory(GsonBuilder builder) {
        return GsonConverterFactory.create(builder.setLenient().create());
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxConverterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    JsonDeserializer<Date> provideJsonDeserializer() {
        return (json, typeOf, context) -> {
            String s = json.getAsJsonPrimitive().getAsString();
            long l = Long.parseLong(s);
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTimeInMillis(l * 1000);

            return calendar.getTime();
        };
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttp(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("BASE_URL") String baseUrl, OkHttpClient okHttpClient, RxJava2CallAdapterFactory factory,
                             Converter.Factory gsonFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(factory)
                .addConverterFactory(gsonFactory)
                .build();
    }

    @Singleton
    @Provides
    Api provideApiService(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Singleton
    @Provides
    ApiDataSource provideApiDataSource(Api api) {
        return new ApiDataSource(api);
    }

    @Singleton
    @Provides
    ApiDataFactory provideApiDataFactory(ApiDataSource source) {
        return new ApiDataFactory(source);
    }
}
