package com.zuhlke.upskilling.departureboard.seku.trainsSearch

import androidx.lifecycle.MutableLiveData
import com.zuhlke.upskilling.departureboard.seku.core.BaseViewModel
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.domain.GetTrainStationUseCase
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainStations

typealias TrainStationsResult = ResultIs<TrainStations>
class MainActivityViewModel(private val trainServiceUseCase: GetTrainStationUseCase) :
    BaseViewModel<TrainStations>() {

    fun getTrainStationData(query: String) {
        trainServiceUseCase
            .execute(query)
            .autoSubscribe()
    }

    override val modelLiveData = MutableLiveData<TrainStationsResult>()
}


