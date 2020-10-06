package com.domain.remote.helper

import android.util.Pair
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.HashMap

/**
 * @author volkanhotur
 */
class AbstractAuthInterceptor(headersMap : Map<String, String>?) : Interceptor {

    private var headersMap : Map<String, String>? = null

    init {
        this.headersMap = headersMap
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request: Request

        val requestBuilder = original.newBuilder().method(original.method, original.body)

        headersMap?.let {
            for (key in it.keys) {
                it[key]?.let { value ->
                    requestBuilder.addHeader(key, value)
                }
            }
        }

        request = requestBuilder.build()

        return chain.proceed(request)
    }
}