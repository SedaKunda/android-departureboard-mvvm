package com.zuhlke.upskilling.departureboard.seku.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zuhlke.upskilling.departureboard.seku.utils.toIO
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

abstract class BaseViewModel<Data> :
    ViewModel() {//base view model makes it easier to create new VMs

    protected val disposable = CompositeDisposable()
    abstract val modelLiveData: MutableLiveData<ResultIs<Data>>

    fun observeData() = modelLiveData as LiveData<ResultIs<Data>>

    fun Single<Data>.autoSubscribe(showProgress: Boolean = true) {
        this
            .toIO()
            .doOnError { Timber.e(it) }
            .doOnSubscribe { if (showProgress) modelLiveData.postValue(ResultIs.Progress) }
            .subscribe({ modelLiveData.postValue(ResultIs.Success<Data>(it)) },
                { modelLiveData.postValue(ResultIs.Error(it)) })
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

sealed class ResultIs<out Data> {
    data class Success<Data>(val data: Data) : ResultIs<Data>()
    object Progress : ResultIs<Nothing>() //singleton
    data class Error(val throwable: Throwable) : ResultIs<Nothing>()
}


