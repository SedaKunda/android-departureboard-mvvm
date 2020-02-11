package com.zuhlke.upskilling.departureboard.seku.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zuhlke.upskilling.departureboard.seku.model.TrainTimes
import com.zuhlke.upskilling.departureboard.seku.network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DepartureViewModel: ViewModel() {

    private var listResponseData: MutableLiveData<TrainTimes> = MutableLiveData()

    fun getTrainData(origin: String, destination: String) : LiveData<TrainTimes> {
        getData(origin, destination)
        return listResponseData
    }

    private fun getData(origin: String, destination: String){
        RetrofitClientInstance.retrofitService.fetch(
            origin,
            RetrofitClientInstance.APP_ID,
            RetrofitClientInstance.APP_KEY,
            destination
        ).enqueue(
            object: Callback<TrainTimes> {
                override fun onFailure(call: Call<TrainTimes>, t: Throwable) {
                    Timber.e("Failure: " + t.message)
                }

                override fun onResponse(call: Call<TrainTimes>,
                                        response: Response<TrainTimes>
                ) {
                    listResponseData.value = response.body()
                    Timber.i("success")
                }
            })
    }
}