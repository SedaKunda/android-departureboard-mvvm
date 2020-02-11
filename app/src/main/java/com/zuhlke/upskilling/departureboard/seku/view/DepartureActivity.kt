package com.zuhlke.upskilling.departureboard.seku.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.databinding.ActivityMainBinding
import com.zuhlke.upskilling.departureboard.seku.databinding.DepartureTimesBinding
import com.zuhlke.upskilling.departureboard.seku.model.TrainTimes
import com.zuhlke.upskilling.departureboard.seku.viewmodel.DepartureViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.departure_times.*
import timber.log.Timber


class DepartureActivity : AppCompatActivity() {

    private lateinit var binding: DepartureTimesBinding

    private val model: DepartureViewModel by lazy {
        ViewModelProvider(this).get(DepartureViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.departure_times)
        val startLocation = intent.getStringExtra("start_location")
        val endLocation = intent.getStringExtra("end_location")
        departureText.text = startLocation
        destinationText.text = endLocation
        model.getTrainData(startLocation, endLocation).observe(this, Observer<TrainTimes> {
            //todo
        })
    }

}
