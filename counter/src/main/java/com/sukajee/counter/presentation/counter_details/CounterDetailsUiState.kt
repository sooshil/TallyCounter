package com.sukajee.counter.presentation.counter_details

import com.sukajee.counter.domain.Counter

data class CounterDetailsUiState(
    val currentCount: Int = 0,
    val isLoading: Boolean = false,
    val currentStep: Int = 1,
    val currentTarget: Int = Int.MAX_VALUE,
    val counterName: String = ""
)
