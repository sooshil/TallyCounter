package com.sukajee.counter.data.repository

import com.sukajee.counter.data.database.CounterDao
import com.sukajee.counter.data.mappers.toCounter
import com.sukajee.counter.data.mappers.toCounterDto
import com.sukajee.counter.domain.Counter
import com.sukajee.counter.domain.CounterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CounterRepositoryImpl(
    private val counterDao: CounterDao
): CounterRepository {
    override fun getAllCounters(): Flow<List<Counter>> {
        return counterDao.getAllCounters()
            .map {
                it.map { counterDto ->
                    counterDto.toCounter()
                }
            }
    }

    override suspend fun getCounter(id: Int): Counter? = withContext(Dispatchers.IO) {
        counterDao.getCounterById(id)?.toCounter()
    }

    override suspend fun upsertCounter(counter: Counter) {
        counterDao.upsertCounter(counter.toCounterDto())
    }

    override suspend fun deleteCounterById(id: Int) {
        counterDao.deleteCounterById(id)
    }

    override suspend fun updateCounter(counterId: Int, currentCount: Int) {
        counterDao.updateCounter(counterId = counterId, currentCount = currentCount)
    }
}