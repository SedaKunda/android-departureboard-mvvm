package com.zuhlke.upskilling.departureboard.seku

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class DepartureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.departure_times)
    }
}
