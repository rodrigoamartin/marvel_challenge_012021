package com.rma.marvelchallenge.core.platform

import com.rma.marvelchallenge.data.ApiRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object ApiOkHttpProvider {

    private var okHttpClient: OkHttpClient? = null

    val client: OkHttpClient
        get() {
            if (okHttpClient == null) {

                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

                val httpClient = OkHttpClient.Builder()
                httpClient.callTimeout(2, TimeUnit.MINUTES)
                httpClient.connectTimeout(2, TimeUnit.MINUTES)
                httpClient.readTimeout(2, TimeUnit.MINUTES)
                httpClient.writeTimeout(2, TimeUnit.MINUTES)
                httpClient.addInterceptor(logging)
                httpClient.addInterceptor(ApiRequestInterceptor())
                okHttpClient = httpClient.build()
                okHttpClient!!
            }
            return okHttpClient!!
        }
}