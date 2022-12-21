package com.artemObrazumov.chemistryclass

import android.app.Application
import com.artemObrazumov.chemistryclass.data.api.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeApiServices()
    }

    private fun initializeApiServices() {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY}
        okHttpClientBuilder.addNetworkInterceptor { chain ->
            chain.proceed(chain.request()
                .newBuilder()
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                .build())
        }.addNetworkInterceptor(logger)
        val retrofit = Retrofit.Builder().baseUrl(API_BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(APIService::class.java)
    }

    companion object {
        lateinit var instance: App
            private set

        lateinit var apiService: APIService
            private set

        // Constants
        const val API_BASE_URL = "http://c930811k.beget.tech/api/"
    }
}