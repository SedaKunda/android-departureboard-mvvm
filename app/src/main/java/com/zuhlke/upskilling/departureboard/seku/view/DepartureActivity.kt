package com.zuhlke.upskilling.departureboard.seku.view

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import com.zuhlke.upskilling.departureboard.seku.utils.hide
import com.zuhlke.upskilling.departureboard.seku.utils.show
import com.zuhlke.upskilling.departureboard.seku.utils.showToastLong
import com.zuhlke.upskilling.departureboard.seku.viewmodel.DepartureViewModel
import com.zuhlke.upskilling.departureboard.seku.viewmodel.TrainTimesResult
import kotlinx.android.synthetic.main.departure_times.*

class DepartureActivity : AppCompatActivity() {

    private lateinit var binding: DepartureTimesBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DepartureListAdapter
    private val startLocation by lazy {  intent.getStringExtra(START_DESTINATION)}
    private val endLocation  by lazy { intent.getStringExtra(END_DESTINATION)}
    private val model: DepartureViewModel by lazy {
        ViewModelProvider(this).get(DepartureViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.departure_times)

        //update text views
        departureText.text = startLocation
        destinationText.text = endLocation

        //observe data
        model.observeTrainData().observe(this, Observer<TrainTimesResult> { data ->
            when(data){
                is TrainTimesResult.Success -> {
                    progressBar.hide()
                    mAdapter.processList(data.data.departures)
                }
                is TrainTimesResult.Progress -> progressBar.show()
                is TrainTimesResult.Error -> {
                    progressBar.hide()
                    showToastLong(R.string.departures_error)
                }
            }
        })
        model.getTrainData(startLocation, endLocation)

        mRecyclerView = recyclerView //link recycler view in ui to this recycler view
        mAdapter = DepartureListAdapter(this) //create adapter
        mRecyclerView.adapter = mAdapter //connect adapter and recycler view
        mRecyclerView.layoutManager = LinearLayoutManager(this) //give recycler view a default layout manager
    }

    companion object { //defines how this activity is to be called. This can be reused
        private const val START_DESTINATION = "start_location"
        private const val END_DESTINATION = "end_location"

        fun call(context: Activity, startDestination: String, endDestination: String)
                = context.startActivity(getIntent(context, startDestination, endDestination))

        private fun getIntent(context: Context, startDestination: String, endDestination: String)
                = Intent(context, DepartureActivity::class.java).apply {
                    putExtra(START_DESTINATION, startDestination)
                    putExtra(END_DESTINATION, endDestination)
                }
    }

}
