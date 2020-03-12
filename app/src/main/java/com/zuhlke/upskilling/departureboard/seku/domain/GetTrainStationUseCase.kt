package com.zuhlke.upskilling.departureboard.seku.domain

import com.zuhlke.upskilling.departureboard.seku.network.api.TrainService
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainStations
import io.reactivex.Single


//isolate this use case so that it is reusable - clean architecture
class GetTrainStationUseCase(private val trainService: TrainService) {

    fun execute(query: String): Single<TrainStations> {
        return trainService
            .getStations(query)
    }

}