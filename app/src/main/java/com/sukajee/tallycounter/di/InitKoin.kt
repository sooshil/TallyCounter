package com.sukajee.tallycounter.di

import android.content.Context
import com.sukajee.counter.di.counterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun initKoin(
    context: Context
) {
    startKoin {
        androidContext(context.applicationContext)
        androidLogger()
        modules(
            listOf(
                counterModule
            )
        )
    }
}