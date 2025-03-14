package com.sukajee.tallycounter

import android.app.Application
import com.sukajee.tallycounter.di.initKoin
import org.koin.core.context.startKoin

class TallyCounterApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}