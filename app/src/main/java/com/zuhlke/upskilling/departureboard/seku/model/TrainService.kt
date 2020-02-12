package com.zuhlke.upskilling.departureboard.seku.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrainService {

    @GET("uk/train/station/{station_code}/live.json")
    fun fetch(
        @Path("station_code") origin: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("calling_at") destination: String
    ): Call<TrainTimes>

}