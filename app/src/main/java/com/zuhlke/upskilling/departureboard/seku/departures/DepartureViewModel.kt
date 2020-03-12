package com.zuhlke.upskilling.departureboard.seku.departures

import androidx.lifecycle.MutableLiveData
import com.zuhlke.upskilling.departureboard.seku.core.BaseViewModel
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.network.client.RetrofitClientInstance
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainTimes

typealias TrainTimesResult = ResultIs<TrainTimes>
//with a factory we don't need to assign instance in the constructor
class DepartureViewModel(private val transportClient: RetrofitClientInstance = RetrofitClientInstance) : BaseViewModel<TrainTimes>() { //pass instance in constructor rather than getData to allow unit testing

    fun getTrainStationData(origin: String, destination: String) {
        transportClient.retrofitService
            .getLiveTimes(origin, destination)
            .autoSubscribe(false)
    }

    override val modelLiveData = MutableLiveData<TrainTimesResult>()
}

