package com.zuhlke.upskilling.departureboard.seku.trainsSearch

import androidx.lifecycle.MutableLiveData
import com.zuhlke.upskilling.departureboard.seku.core.BaseViewModel
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.network.client.RetrofitClientInstance
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainStations

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


