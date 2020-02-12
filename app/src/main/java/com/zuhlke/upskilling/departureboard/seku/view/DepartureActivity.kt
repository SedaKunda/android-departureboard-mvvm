package com.zuhlke.upskilling.departureboard.seku.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.adapters.DepartureListAdapter
import com.zuhlke.upskilling.departureboard.seku.databinding.DepartureTimesBinding
import com.zuhlke.upskilling.departureboard.seku.model.Departures
import com.zuhlke.upskilling.departureboard.seku.model.TrainTimes
import com.zuhlke.upskilling.departureboard.seku.viewmodel.DepartureViewModel
import kotlinx.android.synthetic.main.departure_times.*

class DepartureActivity : AppCompatActivity() {

    private lateinit var binding: DepartureTimesBinding
    private lateinit var mRecyclerView: RecyclerView
    private var mAdapter: DepartureListAdapter? = null

    private val model: DepartureViewModel by lazy {
        ViewModelProvider(this).get(DepartureViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.departure_times)

        //update text
        val startLocation = intent.getStringExtra("start_location")
        val endLocation = intent.getStringExtra("end_location")
        departureText.text = startLocation
        destinationText.text = endLocation

        //observe data
        model.observeTrainData().observe(this, Observer<TrainTimes> { data ->
            setList(data.departures)
        })
        model.getTrainData(startLocation, endLocation)

        //inflate recycler view
        mRecyclerView = recyclerView
        mAdapter = DepartureListAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setList(departures: Departures) {
        mAdapter?.processList(departures)
    }

}
