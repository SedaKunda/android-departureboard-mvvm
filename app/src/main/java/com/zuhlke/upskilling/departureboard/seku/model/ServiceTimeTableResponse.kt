package com.zuhlke.upskilling.departureboard.seku.model

data class ServiceTimetableDetails (

    val service : Int,
    val train_uid : String,
    val headcode : String,
    val toc : Toc,
    val train_status : String,
    val origin_name : String,
    val destination_name : String,
    val stop_of_interest : String,
    val date : String,
    val time_of_day : String,
    val mode : String,
    val request_time : String,
    val category : String,
    val operator : String,
    val operator_name : String,
    val stops : List<Stops>
)

data class Stops (

    val station_code : String,
    val tiploc_code : String,
    val station_name : String,
    val stop_type : String,
    val platform : String,
    val aimed_departure_date : String,
    val aimed_departure_time : String,
    val aimed_arrival_date : String,
    val aimed_arrival_time : String,
    val aimed_pass_date : String,
    val aimed_pass_time : String
)

data class Toc (

    val atoc_code : String
)

