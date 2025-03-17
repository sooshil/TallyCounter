package com.sukajee.counter.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sukajee.counter.data.dto.CounterDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {
    @Query("SELECT * FROM counter ORDER BY id DESC")
    fun getAllCounters(): Flow<List<CounterDto>>

    @Query("SELECT * FROM counter WHERE id = :id")
    fun getCounterById(id: Int): CounterDto?

    @Upsert
    suspend fun upsertCounter(counter: CounterDto)

    @Query("DELETE FROM counter WHERE id = :id")
    suspend fun deleteCounterById(id: Int)

    @Query("UPDATE counter SET currentCount = :currentCount WHERE id = :counterId")
    suspend fun updateCounter(counterId: Int, currentCount: Int)
}