package com.zuhlke.upskilling.departureboard.seku

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.domain.GetTrainStationUseCase
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainStations
import com.zuhlke.upskilling.departureboard.seku.rule.RxImmediateSchedulerRule
import com.zuhlke.upskilling.departureboard.seku.trainsSearch.MainActivityViewModel
import com.zuhlke.upskilling.departureboard.seku.trainsSearch.TrainStationsResult
import com.zuhlke.upskilling.departureboard.seku.utils.TestException
import com.zuhlke.upskilling.departureboard.seku.utils.TrainStationFactory
import com.zuhlke.upskilling.departureboard.seku.utils.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainActivityViewModelTest {


    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule() //useful for livedata

    @Mock
    private lateinit var trainService: GetTrainStationUseCase
    private lateinit var testSubject: MainActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSubject = MainActivityViewModel(trainService)
    }

    //this test is similar to gettrainStationusecaseTest. Same thing tested but using different approach (no test subscriber)
    @Test
    fun `result is success with network success`() {
        val observer = mock<Observer<TrainStationsResult>>()
        testSubject.modelLiveData.observeForever(observer)
        stubTrainServiceSuccess(TrainStationFactory.provideTrainStations())
        testSubject.getTrainStationData(QUERY)
        verify(observer).onChanged(ResultIs.Progress)
        verify(observer).onChanged(ResultIs.Success(TrainStationFactory.provideTrainStations()))
    }

    @Test
    fun `result is error with network error`() {
        val observer = mock<Observer<TrainStationsResult>>()
        testSubject.modelLiveData.observeForever(observer)
        val expectedError = TestException()
        stubTrainServiceError(expectedError)
        testSubject.getTrainStationData(QUERY)
        verify(observer).onChanged(ResultIs.Progress)
        verify(observer).onChanged(ResultIs.Error(expectedError))
    }

    private fun stubTrainServiceSuccess(result: TrainStations, query: String = QUERY) {
        `when`(trainService.execute(query))
            .thenReturn(Single.just(result))
    }

    private fun stubTrainServiceError(error: Throwable, query: String = QUERY) {
        `when`(trainService.execute(query))
            .thenReturn(Single.error(error))
    }

    companion object {
        const val QUERY = "London"
    }
}




