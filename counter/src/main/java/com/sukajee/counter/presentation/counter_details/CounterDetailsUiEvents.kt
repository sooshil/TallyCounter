package com.sukajee.counter.presentation.counter_details

import com.sukajee.counter.domain.Counter
import com.sukajee.counter.presentation.counter_list.CounterId

sealed class CounterDetailsUiEvents {
    data object OnAddCounterClicked : CounterDetailsUiEvents()
}