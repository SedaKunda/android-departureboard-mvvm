package com.zuhlke.upskilling.departureboard.seku.utils

import com.zuhlke.upskilling.departureboard.seku.network.model.TrainStations

object TrainStationFactory {


    fun provideTrainStations() = TrainStations(
        request_time = "",
        source = "",
        acknowledgements = "",
        member = emptyList()
    )
}

class TestException(override val message: String? = "some error message" ) : Exception()