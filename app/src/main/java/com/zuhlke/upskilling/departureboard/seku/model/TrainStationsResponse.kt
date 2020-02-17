package com.zuhlke.upskilling.departureboard.seku.model

data class TrainStations (

	val request_time : String,
	val source : String,
	val acknowledgements : String,
	val member : List<StationDetails>
)

data class StationDetails (

	val type : String,
	val name : String,
	val latitude : Double,
	val longitude : Double,
	val accuracy : Int,
	val station_code : String,
	val tiploc_code : String
)