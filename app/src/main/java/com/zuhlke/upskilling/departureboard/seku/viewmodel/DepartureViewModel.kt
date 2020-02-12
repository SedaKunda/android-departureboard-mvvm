package com.zuhlke.upskilling.departureboard.seku.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zuhlke.upskilling.departureboard.seku.BuildConfig
import com.zuhlke.upskilling.departureboard.seku.model.TrainTimes
import com.zuhlke.upskilling.departureboard.seku.network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DepartureViewModel : ViewModel() {

    private var listTrainTimes: MutableLiveData<TrainTimes> = MutableLiveData()

    fun getTrainData(origin: String, destination: String) {
        getData(origin, destination)
    }

    fun observeTrainData(): LiveData<TrainTimes> = listTrainTimes

    private fun getData(origin: String, destination: String) {
        RetrofitClientInstance.retrofitService.fetch(
            origin,
            BuildConfig.APP_ID,
            BuildConfig.APP_KEY,
            destination
        ).enqueue(
            object : Callback<TrainTimes> {
                override fun onFailure(call: Call<TrainTimes>, t: Throwable) {
                    Timber.e("Failure: " + t.message)
                }
                override fun onResponse(
                    call: Call<TrainTimes>,
                    response: Response<TrainTimes>
                ) {
                    listTrainTimes.value = response.body()
                    Timber.i("success")
                }
            })
    }
}