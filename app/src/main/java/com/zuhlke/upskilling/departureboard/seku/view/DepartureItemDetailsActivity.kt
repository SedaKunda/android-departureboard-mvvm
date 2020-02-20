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
import com.zuhlke.upskilling.departureboard.seku.adapters.StopsListAdapter
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.databinding.ActivityDepartureItemDetailsBinding
import com.zuhlke.upskilling.departureboard.seku.model.All
import com.zuhlke.upskilling.departureboard.seku.model.Stops
import com.zuhlke.upskilling.departureboard.seku.utils.hide
import com.zuhlke.upskilling.departureboard.seku.utils.show
import com.zuhlke.upskilling.departureboard.seku.utils.showToastLong
import com.zuhlke.upskilling.departureboard.seku.viewmodel.DepartureItemDetailsViewModel
import com.zuhlke.upskilling.departureboard.seku.viewmodel.TimetableResult
import kotlinx.android.synthetic.main.activity_departure_item_details.*

class DepartureItemDetailsActivity: AppCompatActivity() {
    private val allList : All by lazy { intent.getParcelableExtra<All>("all")}
    private lateinit var mAdapter: StopsListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var binding: ActivityDepartureItemDetailsBinding
    private var stopsList: List<Stops> = emptyList()
    val model: DepartureItemDetailsViewModel by lazy {
        ViewModelProvider(this).get(DepartureItemDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_departure_item_details)
        originValue.text = allList.origin_name
        destinationValue.text = allList.destination_name
        platformValue.text = allList.platform.toString()
        operatorValue.text = allList.operator
        aimedDepartureValue.text = allList.aimed_departure_time
        expectedDepartureValue.text = allList.expected_departure_time

        model.observeData().observe(this, Observer<TimetableResult> { data ->
            when(data){
                is ResultIs.Success -> {
                    progressBar.hide()
                    mAdapter.processList(data.data)
                }
                is ResultIs.Progress -> {progressBar.show()}
                is ResultIs.Error -> {
                    progressBar.hide()
                    showToastLong(R.string.departures_error)
                }
            }
        })
        model.getTimeTable(allList.service_timetable.id)

        mRecyclerView = stopsRecyclerView
        mAdapter = StopsListAdapter(stopsList)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    companion object { //defines how this activity is to be called. This can be reused

        fun call(context: Activity, all: All
        ) = context.startActivity(
            getIntent(context, all))

        private fun getIntent(context: Context, all: All
        ) = Intent(context, DepartureItemDetailsActivity::class.java).apply {
            putExtra("all", all)
        }
    }
}
