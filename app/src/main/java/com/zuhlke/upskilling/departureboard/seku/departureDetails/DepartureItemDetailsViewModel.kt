package com.zuhlke.upskilling.departureboard.seku.departureDetails

import androidx.lifecycle.MutableLiveData
import com.zuhlke.upskilling.departureboard.seku.core.BaseViewModel
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.network.client.CoroutinesClientInstance
import com.zuhlke.upskilling.departureboard.seku.network.model.ServiceTimetableDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

typealias TimetableResult = ResultIs<ServiceTimetableDetails>

class DepartureItemDetailsViewModel(private val transportClient: CoroutinesClientInstance = CoroutinesClientInstance) :
    BaseViewModel<ServiceTimetableDetails>() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    override val modelLiveData = MutableLiveData<TimetableResult>()

    fun getTimeTable(url: String) {
        coroutineScope.launch {
            try {
                modelLiveData.postValue(ResultIs.Progress)
                transportClient
                    .retrofitServiceWithCoroutine
                    .getServiceTimeTable(url)
                    .run {
                        if (isSuccessful )modelLiveData.postValue(ResultIs.Success(this.body()!!))
                    }

            } catch (e: Exception) {
                Timber.e(e)
                modelLiveData.postValue(ResultIs.Error(e.cause!!))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}