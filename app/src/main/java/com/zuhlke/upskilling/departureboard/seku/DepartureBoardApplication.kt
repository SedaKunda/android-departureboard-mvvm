package com.zuhlke.upskilling.departureboard.seku

import android.app.Application
import timber.log.Timber

class DepartureBoardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}