package com.zuhlke.upskilling.departureboard.seku.model

data class TrainTimes(

    val date : String,
    val time_of_day : String,
    val request_time : String,
    val station_name : String,
    val station_code : String,
    val departures : Departures
)

data class All(
    val mode : String,
    val service : Int,
    val train_uid : String,
    val platform : Int,
    val operator : String,
    val operator_name : String,
    val aimed_departure_time : String,
    val aimed_arrival_time : String,
    val aimed_pass_time : String,
    val origin_name : String,
    val destination_name : String,
    val source : String,
    val category : String,
    val service_timetable : ServiceTimetable,
    val status : String,
    val expected_arrival_time : String,
    val expected_departure_time : String,
    val best_arrival_estimate_mins : Int,
    val best_departure_estimate_mins : Int
)

data class Departures (
    val all : List<All>
)

data class ServiceTimetable (

    val id : String
)