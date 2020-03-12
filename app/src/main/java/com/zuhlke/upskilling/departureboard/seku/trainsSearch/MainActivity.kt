package com.zuhlke.upskilling.departureboard.seku.trainsSearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.core.utils.checkRequiredField
import com.zuhlke.upskilling.departureboard.seku.core.utils.getView
import com.zuhlke.upskilling.departureboard.seku.core.utils.showToastLong
import com.zuhlke.upskilling.departureboard.seku.departures.DepartureActivity
import com.zuhlke.upskilling.departureboard.seku.domain.GetTrainStationUseCase
import com.zuhlke.upskilling.departureboard.seku.network.client.RetrofitClientInstance
import com.zuhlke.upskilling.departureboard.seku.network.model.StationDetails
import com.zuhlke.upskilling.departureboard.seku.trainsSearch.autocompleteList.StationListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val factory = MainViewModelFactory(GetTrainStationUseCase(RetrofitClientInstance.retrofitService))
    //initialise view model with factory
    private val model: MainActivityViewModel by lazy {
        ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
    }
    private lateinit var endLocationCode: String
    private lateinit var startLocationCode: String
    private lateinit var endLocationName: String
    private lateinit var startLocationName: String
    var stationDetails: List<StationDetails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

        val adapter =
            StationListAdapter(
                this,
                android.R.layout.test_list_item,
                stationDetails
            )
        end_location.setAdapter(adapter)
        start_location.setAdapter(adapter)

        model.observeData().observe(this, Observer<TrainStationsResult> { data ->
            when (data) {
                is ResultIs.Success -> {
                    adapter.processList(data.data.member)
                }
                is ResultIs.Error -> {
                    showToastLong(R.string.departures_error)
                }
            }
        })

        start_location.doOnTextChanged { text, _, _, _ ->
            model.getTrainStationData(text.toString())
        }
        start_location.setOnItemClickListener { _, view, _, _ ->
            startLocationCode = view.getView(R.id.station_code)
            startLocationName = view.getView(R.id.station_name)
            start_location.setText(startLocationName)
        }
        end_location.doOnTextChanged { text, _, _, _ ->
            model.getTrainStationData(text.toString())
        }
        end_location.setOnItemClickListener { _, view, _, _ ->
            endLocationCode = view.getView(R.id.station_code)
            endLocationName = view.getView(R.id.station_name)
            end_location.setText(endLocationName)
        }

        getTimesButton.setOnClickListener {
            when {
                checkRequiredField(
                    start_location
                ) -> showToastLong(R.string.invalidInputForOriginWarning)
                checkRequiredField(
                    end_location
                ) -> showToastLong(R.string.invalidInputForDestinationWarning)
                else -> getTransportTimes()
            }
        }
    }

    private fun getTransportTimes() { //DepartureActivity defines what is required for it to be called (clean architecture). call is also reusable
        DepartureActivity.call(
            this,
            startLocationCode,
            endLocationCode,
            startLocationName,
            endLocationName
        )
    }
}
