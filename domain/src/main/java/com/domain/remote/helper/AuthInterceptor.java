package com.domain.remote.helper;

import android.util.Pair;

import java.io.IOException;
import java.util.Set;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Request request;

        Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());

        if (headers() != null)
            for (Pair<String, String> header : headers()) {
                requestBuilder.addHeader(header.first, header.second);
            }

        if (token() != null)
            requestBuilder.addHeader(token().first, token().second);


        request = requestBuilder.build();

        return chain.proceed(request);
    }

    public abstract Pair<String, String> token();

    public abstract Set<Pair<String, String>> headers();


}
