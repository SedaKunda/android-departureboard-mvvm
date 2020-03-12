package com.zuhlke.upskilling.departureboard.seku.trainsSearch
/**I
 * https://stackoverflow.com/questions/54419236/why-a-viewmodel-factory-is-needed-in-android
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zuhlke.upskilling.departureboard.seku.domain.GetTrainStationUseCase

class MainViewModelFactory(private val retrofitService: GetTrainStationUseCase) : ViewModelProvider.Factory {

    //a factory is useful to overide default providers moethod when creating an instance of the view model
    // if we need to pass some data in the constructor view model, we cant do this when using default viewmodelProviders
    @Throws(IllegalArgumentException::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            MainActivityViewModel(retrofitService) as T
        else throw IllegalArgumentException("ViewModel not found")
    }
}