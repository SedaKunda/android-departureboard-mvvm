package com.zuhlke.upskilling.departureboard.seku.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zuhlke.upskilling.departureboard.seku.model.TrainTimes
import com.zuhlke.upskilling.departureboard.seku.network.RetrofitClientInstance
import com.zuhlke.upskilling.departureboard.seku.utils.toIO
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class DepartureViewModel(private val transportClient: RetrofitClientInstance = RetrofitClientInstance) : ViewModel() { //pass instance in constructor rather than getData to allow unit testing

    private var listTrainTimes: MutableLiveData<TrainTimesResult> = MutableLiveData()
    private val disposable = CompositeDisposable()

    fun getTrainData(origin: String, destination: String) {
        getData(origin, destination)
    }

    fun observeTrainData(): LiveData<TrainTimesResult> = listTrainTimes

    private fun getData(origin: String, destination: String) {
        transportClient.retrofitService
            .getLiveTimes(origin, destination)
            .toIO()
            .doOnSubscribe { listTrainTimes.postValue(TrainTimesResult.Progress) }
            .doOnError { Timber.e(it) }
            .subscribe(
                { listTrainTimes.postValue(TrainTimesResult.Success(it)) },
                { listTrainTimes.postValue(TrainTimesResult.Error(it)) })
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

sealed class TrainTimesResult {
    data class Success(val data: TrainTimes) : TrainTimesResult()
    object Progress : TrainTimesResult() //singleton
    data class Error(val throwable: Throwable) : TrainTimesResult()
}

