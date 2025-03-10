package com.sukajee.counter.presentation.counter_details

data class UiState(
    val currentCount: Int = 0,
    val target: Int = Int.MAX_VALUE,
    val steps: Int = 1,
    val hasTargetReached: Boolean = false
)
