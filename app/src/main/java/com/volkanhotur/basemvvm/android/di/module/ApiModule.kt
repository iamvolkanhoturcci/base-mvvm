package com.volkanhotur.basemvvm.android.di.module

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.volkanhotur.basemvvm.BuildConfig
import com.volkanhotur.basemvvm.android.data.repository.Api
import com.volkanhotur.basemvvm.android.data.repository.ApiDataFactory
import com.volkanhotur.basemvvm.android.data.repository.ApiDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author volkanhotur
 */
@Module
class ApiModule {

    @Singleton
    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String {
        return "https://api.github.com/"
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(dateJsonDeserializer: JsonDeserializer<Date?>): GsonBuilder {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Date::class.java, dateJsonDeserializer)
        return gsonBuilder
    }

    @Singleton
    @Provides
    fun provideConverterFactory(builder: GsonBuilder): Converter.Factory {
        return GsonConverterFactory.create(builder.setLenient().create())
    }

    @Singleton
    @Provides
    fun provideRxConverterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideJsonDeserializer(): JsonDeserializer<Date> {
        return JsonDeserializer { json: JsonElement, typeOf: Type?, context: JsonDeserializationContext? ->
            val s = json.asJsonPrimitive.asString
            val l = s.toLong()
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.timeInMillis = l * 1000
            calendar.time
        }
    }

    @Singleton
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BASE_URL") baseUrl: String, okHttpClient: OkHttpClient, factory: RxJava2CallAdapterFactory,
                        gsonFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(factory)
                .addConverterFactory(gsonFactory)
                .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideApiDataSource(api: Api): ApiDataSource {
        return ApiDataSource(api)
    }

    @Singleton
    @Provides
    fun provideApiDataFactory(source: ApiDataSource): ApiDataFactory {
        return ApiDataFactory(source)
    }
}