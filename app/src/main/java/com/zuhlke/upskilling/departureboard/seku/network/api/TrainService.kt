package com.zuhlke.upskilling.departureboard.seku.network.api

import com.zuhlke.upskilling.departureboard.seku.BuildConfig
import com.zuhlke.upskilling.departureboard.seku.network.model.ServiceTimetableDetails
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainStations
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainTimes
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface TrainService {

    @GET("uk/train/station/{station_code}/live.json")
    fun getLiveTimes(
        @Path("station_code") origin: String,
        @Query("calling_at") destination: String,
        @Query("app_id") appId: String = BuildConfig.APP_ID,
        @Query("app_key") appKey: String = BuildConfig.APP_KEY
    ): Single<TrainTimes>

    @GET("uk/places.json")
    fun getStations(
        @Query("query") query: String,
        @Query("type") type: String = "train_station",
        @Query("app_id") appId: String = BuildConfig.APP_ID,
        @Query("app_key") appKey: String = BuildConfig.APP_KEY
    ): Single<TrainStations>


}

interface CTrainService {
    @GET
    suspend fun getServiceTimeTable(
        @Url url: String, @Query("app_id") appId: String = BuildConfig.APP_ID,
        @Query("app_key") appKey: String = BuildConfig.APP_KEY
    ): Response<ServiceTimetableDetails>

}

