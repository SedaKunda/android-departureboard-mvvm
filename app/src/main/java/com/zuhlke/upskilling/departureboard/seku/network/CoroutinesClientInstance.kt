package com.zuhlke.upskilling.departureboard.seku.network

import com.zuhlke.upskilling.departureboard.seku.model.CTrainService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoroutinesClientInstance {
    var BASE_URL = "https://transportapi.com/v3/"

    private val logging =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()


    private val retrofitWithCoroutine = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitServiceWithCoroutine: CTrainService by lazy {
        retrofitWithCoroutine.create(CTrainService::class.java)
    }
}


