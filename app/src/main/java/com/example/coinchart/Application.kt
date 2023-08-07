package com.example.coinchart

import android.app.Application
import android.content.Context
import timber.log.Timber

class Application : Application() {

    init {
        instance = this
    }

    companion object{

        private var instance : Application? = null

        fun context() : Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}