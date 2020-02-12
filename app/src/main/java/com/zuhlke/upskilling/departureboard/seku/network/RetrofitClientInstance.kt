package com.zuhlke.upskilling.departureboard.seku.network

import com.zuhlke.upskilling.departureboard.seku.model.TrainService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {
    private const val BASE_URL = "https://transportapi.com/v3/"

    val logging = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    val client = OkHttpClient.Builder().addInterceptor(logging).build()

    private val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

    val retrofitService : TrainService by lazy {
            retrofit.create(TrainService::class.java) }


}