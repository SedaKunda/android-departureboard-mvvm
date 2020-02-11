package com.zuhlke.upskilling.departureboard.seku.network

import com.zuhlke.upskilling.departureboard.seku.model.TrainService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {
    private const val BASE_URL = "https://transportapi.com/v3/"
    const val APP_KEY = "7a0e1569791814fba272d00fe5a8f0b2"
    const val APP_ID = "5262611a"

    private val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    val retrofitService : TrainService by lazy {
            retrofit.create(TrainService::class.java) }
}