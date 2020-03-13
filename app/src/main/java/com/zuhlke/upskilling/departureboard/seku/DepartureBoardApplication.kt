package com.zuhlke.upskilling.departureboard.seku

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class DepartureBoardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());
    }
}