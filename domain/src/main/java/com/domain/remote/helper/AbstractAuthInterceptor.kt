package com.domain.remote.helper

import android.util.Pair
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * @author volkanhotur
 */
abstract class AbstractAuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request: Request

        val requestBuilder = original.newBuilder().method(original.method(), original.body())

        headers()?.let {
            for (header in it) {
                requestBuilder.addHeader(header.first, header.second)
            }
        }

        token()?.let {
            requestBuilder.addHeader(it.first, it.second)
        }

        request = requestBuilder.build()

        return chain.proceed(request)
    }

    abstract fun token(): Pair<String, String>?

    abstract fun headers(): Set<Pair<String, String>>?
}