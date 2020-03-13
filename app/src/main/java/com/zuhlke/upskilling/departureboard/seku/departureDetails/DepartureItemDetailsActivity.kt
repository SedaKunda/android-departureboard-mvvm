package com.zuhlke.upskilling.departureboard.seku.departureDetails

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.core.utils.hide
import com.zuhlke.upskilling.departureboard.seku.core.utils.setDivider
import com.zuhlke.upskilling.departureboard.seku.core.utils.show
import com.zuhlke.upskilling.departureboard.seku.core.utils.showToastLong
import com.zuhlke.upskilling.departureboard.seku.departureDetails.stopsList.StopsListAdapter
import com.zuhlke.upskilling.departureboard.seku.network.model.All
import com.zuhlke.upskilling.departureboard.seku.network.model.Stops
import kotlinx.android.synthetic.main.activity_departure_item_details.*

class DepartureItemDetailsActivity: AppCompatActivity() {
    private val allList : All by lazy { intent.getParcelableExtra<All>("all")}
    private lateinit var mAdapter: StopsListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private var stopsList: List<Stops> = emptyList()
    val model: DepartureItemDetailsViewModel by lazy {
        ViewModelProvider(this).get(DepartureItemDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departure_item_details)
        originValue.text = allList.origin_name
        destinationValue.text = allList.destination_name
        platformValue.text = allList.platform.toString()
        operatorValue.text = allList.operator_name
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
        mAdapter =
            StopsListAdapter(
                stopsList
            )
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setDivider(R.drawable.row_item_divider)
    }

    companion object { //defines how this activity is to be called. This can be reused

        fun call(context: Activity, all: All
        ) = context.startActivity(
            getIntent(
                context,
                all
            )
        )

        private fun getIntent(context: Context, all: All
        ) = Intent(context, DepartureItemDetailsActivity::class.java).apply {
            putExtra("all", all)
        }
    }

    //todo could improve further by showing only stops between what user requested rather than all stops a train makes
}
