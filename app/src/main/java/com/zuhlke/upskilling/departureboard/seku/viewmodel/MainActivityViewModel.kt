package com.zuhlke.upskilling.departureboard.seku.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zuhlke.upskilling.departureboard.seku.core.BaseViewModel
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.model.TrainStations
import com.zuhlke.upskilling.departureboard.seku.network.RetrofitClientInstance

typealias TrainStationsResult = ResultIs<TrainStations>
class MainActivityViewModel(private val transportClient: RetrofitClientInstance = RetrofitClientInstance) :
    BaseViewModel<TrainStations>() {

    fun getTrainStationData(query: String) {
        transportClient.retrofitService
            .getStations(query)
            .autoSubscribe()
    }

    override val modelLiveData = MutableLiveData<TrainStationsResult>()
}


