package com.rma.marvelchallenge.data

import com.rma.marvelchallenge.core.platform.Connection
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class ApiRequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", Connection.API_PUBLIC_KEY)
            .addQueryParameter("hash", Connection.API_HASH)
            .addQueryParameter("ts", Connection.API_TS)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}