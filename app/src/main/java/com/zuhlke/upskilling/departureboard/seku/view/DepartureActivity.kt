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
import com.zuhlke.upskilling.departureboard.seku.adapters.OnItemClickListener
import com.zuhlke.upskilling.departureboard.seku.core.ResultIs
import com.zuhlke.upskilling.departureboard.seku.databinding.ActivityDepartureBinding
import com.zuhlke.upskilling.departureboard.seku.model.All
import com.zuhlke.upskilling.departureboard.seku.utils.hide
import com.zuhlke.upskilling.departureboard.seku.utils.show
import com.zuhlke.upskilling.departureboard.seku.utils.showToastLong
import com.zuhlke.upskilling.departureboard.seku.viewmodel.DepartureViewModel
import com.zuhlke.upskilling.departureboard.seku.viewmodel.TrainTimesResult
import kotlinx.android.synthetic.main.activity_departure.*

class DepartureActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityDepartureBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DepartureListAdapter
    private val originCode by lazy { intent.getStringExtra(ORIGIN_CODE) }
    private val destinationCode by lazy { intent.getStringExtra(DESTINATION_CODE) }
    private val originName by lazy { intent.getStringExtra(ORIGIN_NAME) }
    private val destinationName by lazy { intent.getStringExtra(DESTINATION_NAME) }
    private val model: DepartureViewModel by lazy {
        ViewModelProvider(this).get(DepartureViewModel::class.java)
    }
    private var departureList: List<All> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_departure)

        //update text views
        departureText.text = originName
        destinationText.text = destinationName

        //observe data
        model.observeData().observe(this, Observer<TrainTimesResult> { data ->
            when (data) {
                is ResultIs.Success -> {
                    progressBar.hide()
                    mAdapter.processList(data.data.departures)
                }
                is ResultIs.Progress -> {progressBar.show()}
                is ResultIs.Error -> {
                    progressBar.hide()
                    showToastLong(R.string.departures_error)
                }
            }
        })
        model.getTrainStationData(originCode, destinationCode)

        mRecyclerView = recyclerView //link recycler view in ui to this recycler view
        mAdapter = DepartureListAdapter(departureList, this) //create adapter
        mRecyclerView.adapter = mAdapter //connect adapter and recycler view
        mRecyclerView.layoutManager = LinearLayoutManager(this) //give recycler view a default layout manager
    }

    override fun onItemClicked(all: All) {
        DepartureItemDetailsActivity.call(this, all)
    }

    companion object { //defines how this activity is to be called. This can be reused
        private const val ORIGIN_CODE = "start_location_code"
        private const val DESTINATION_CODE = "end_location_code"
        private const val ORIGIN_NAME = "start_location_name"
        private const val DESTINATION_NAME = "end_location_name"

        fun call(context: Activity, originCode: String, destinationCode: String, originName: String, destinationName: String)
                = context.startActivity(getIntent(context, originCode, destinationCode, originName, destinationName)
        )

        private fun getIntent(context: Context, originCode: String, destinationCode: String, originName: String, destinationName: String)
                = Intent(context, DepartureActivity::class.java).apply {
                    putExtra(ORIGIN_CODE, originCode)
                    putExtra(DESTINATION_CODE, destinationCode)
                    putExtra(ORIGIN_NAME, originName)
                    putExtra(DESTINATION_NAME, destinationName)
        }
    }

}
