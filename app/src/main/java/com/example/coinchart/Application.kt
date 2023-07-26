package com.example.coinchart

import android.app.Application
import timber.log.Timber

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}