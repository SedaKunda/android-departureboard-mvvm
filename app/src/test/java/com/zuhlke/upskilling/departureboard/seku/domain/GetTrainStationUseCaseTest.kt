package com.zuhlke.upskilling.departureboard.seku.domain

import com.zuhlke.upskilling.departureboard.seku.network.api.TrainService
import com.zuhlke.upskilling.departureboard.seku.network.model.TrainStations
import com.zuhlke.upskilling.departureboard.seku.rule.RxImmediateSchedulerRule
import com.zuhlke.upskilling.departureboard.seku.utils.TrainStationFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetTrainStationUseCaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var trainService: TrainService
    private lateinit var testSubject: GetTrainStationUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSubject = GetTrainStationUseCase(trainService)
    }

    @Test
    fun `completes execution of getStationUseCaseTest using test subscriber`() {
        stubTrainServiceSuccess(TrainStationFactory.provideTrainStations())
        testSubject.execute(QUERY).test().assertComplete()
        testSubject.execute(QUERY).test().assertResult(TrainStationFactory.provideTrainStations())
    }

    private fun stubTrainServiceSuccess(result: TrainStations, query: String =QUERY) {
        Mockito.`when`(trainService.getStations(query))
            .thenReturn(Single.just(result))
    }

    companion object {
        const val QUERY = "London"
    }
}