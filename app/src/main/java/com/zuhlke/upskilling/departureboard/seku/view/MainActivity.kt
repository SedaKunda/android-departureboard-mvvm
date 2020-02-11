package com.zuhlke.upskilling.departureboard.seku.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        getTimesButton.setOnClickListener {
            getTransportTimes()
        }
    }

    private fun getTransportTimes() {
        val departureIntent = Intent(this, DepartureActivity::class.java)
        departureIntent.putExtra("start_location", start_location.text.toString())
        departureIntent.putExtra("end_location", end_location.text.toString())
        startActivity(departureIntent)
    }
}
