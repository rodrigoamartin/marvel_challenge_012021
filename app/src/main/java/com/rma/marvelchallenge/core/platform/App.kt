package com.rma.marvelchallenge.core.platform

import android.app.Application

class App : Application() {

    companion object {

        @get:Synchronized
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val baseUrl: String
        get() = Connection.BASE_URL
}