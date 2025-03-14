package com.sukajee.counter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sukajee.counter.data.dto.CounterDto

@Database(entities = [CounterDto::class], version = 1)
abstract class CounterDatabase: RoomDatabase() {
    abstract val counterDao: CounterDao
}