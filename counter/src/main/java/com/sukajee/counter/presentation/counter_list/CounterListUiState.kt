package com.sukajee.counter.presentation.counter_list

import com.sukajee.counter.domain.Counter

data class CounterListUiState(
    val counters: List<Counter> = emptyList(),
    val isLoading: Boolean = false
)
