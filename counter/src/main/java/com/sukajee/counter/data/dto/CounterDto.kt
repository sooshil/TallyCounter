package com.sukajee.counter.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class CounterDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "Counter",
    val currentCount: Int = 0,
    val target: Int = Int.MAX_VALUE,
    val steps: Int = 1,
    val isPinned: Boolean = false
)
