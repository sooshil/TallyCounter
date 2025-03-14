package com.sukajee.counter.di

import androidx.room.Room
import com.sukajee.counter.data.database.CounterDao
import com.sukajee.counter.data.database.CounterDatabase
import com.sukajee.counter.domain.CounterRepository
import com.sukajee.counter.data.repository.CounterRepositoryImpl
import com.sukajee.counter.presentation.counter_list.CounterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val counterModule = module {

    single<CounterDatabase> {
        Room.databaseBuilder(
            androidContext(),
            CounterDatabase::class.java,
            "counter_database"
        ).build()
    }

    single<CounterDao> {
        get<CounterDatabase>().counterDao
    }

    single<CounterRepository> {
        CounterRepositoryImpl(get())
    }

    viewModelOf(::CounterViewModel)
}