package com.zuhlke.upskilling.departureboard.seku.model

import com.zuhlke.upskilling.departureboard.seku.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrainService {

    @GET("uk/train/station/{station_code}/live.json")
    fun getLiveTimes(
        @Path("station_code") origin: String,
        @Query("calling_at") destination: String,
        @Query("app_id") appId: String = BuildConfig.APP_ID,
        @Query("app_key") appKey: String = BuildConfig.APP_KEY
    ): Single<TrainTimes>

}

