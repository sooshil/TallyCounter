package com.sukajee.tallycounter

import android.app.Application
import com.sukajee.tallycounter.di.initKoin

class CounterApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(applicationContext)
    }
}