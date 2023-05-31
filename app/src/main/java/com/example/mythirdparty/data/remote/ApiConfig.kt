package com.example.mythirdparty.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val baseUrl = "https://api.github.com/"
        private const val token = "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"

        fun getApiService(): ApiService {
            val loggingInterceptor =
//                if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            }
            val authInterceptor = Interceptor { chain ->
                val request = chain.request()
                val headers = request.newBuilder()
                    .addHeader("Authorization",token)
                    .build()
                chain.proceed(headers)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)

        }
    }
}