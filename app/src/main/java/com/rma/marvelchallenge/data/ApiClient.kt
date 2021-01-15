package com.rma.marvelchallenge.data

import com.rma.marvelchallenge.core.platform.ApiOkHttpProvider
import com.rma.marvelchallenge.core.platform.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{

        private var apiService: ApiService? = null

        fun getApiService(): ApiService {
            if (apiService == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(App.instance.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ApiOkHttpProvider.client)
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
