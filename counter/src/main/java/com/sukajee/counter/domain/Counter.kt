package com.sukajee.counter.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Counter(
    val id: Int = 0,
    val name: String,
    val currentCount: Int,
    val target: Int,
    val steps: Int,
    val isPinned: Boolean
)
