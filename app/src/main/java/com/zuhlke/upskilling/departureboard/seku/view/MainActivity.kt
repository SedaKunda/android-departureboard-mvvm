package com.zuhlke.upskilling.departureboard.seku.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.databinding.ActivityMainBinding
import com.zuhlke.upskilling.departureboard.seku.utils.checkRequiredField
import com.zuhlke.upskilling.departureboard.seku.utils.showToastLong
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        getTimesButton.setOnClickListener {
            when {
                checkRequiredField(start_location) -> showToastLong(R.string.invalidInputForOriginWarning)
                checkRequiredField(end_location) -> showToastLong(R.string.invalidInputForDestinationWarning)
                else -> getTransportTimes()
            }
        }
    }

    private fun getTransportTimes() { //DepartureActivity defines what is required for it to be called (clean architecture). call is also reusable
        DepartureActivity.call(
            this,
            start_location.text.toString(),
            end_location.text.toString()
        )
    }
}
