package com.sukajee.counter.domain

import kotlinx.coroutines.flow.Flow

interface CounterRepository {
    fun getAllCounters(): Flow<List<Counter>>
    suspend fun getCounter(id: Int): Counter?
    suspend fun upsertCounter(counter: Counter)
    suspend fun deleteCounterById(id: Int)
    suspend fun updateCounter(counterId: Int, currentCount: Int)
}