package com.sukajee.counter.presentation.counter_list

import com.sukajee.counter.domain.Counter

typealias CounterId = Int


sealed class CounterListUiEvents {
    data object OnAddCounterClicked : CounterListUiEvents()
    data class OnCounterClicked(val counterId: CounterId) : CounterListUiEvents()
    data class OnDeleteCounterClicked(val counter: Counter) : CounterListUiEvents()
    data class OnPinCounterClicked(val counter: Counter) : CounterListUiEvents()
    data class OnPlusClicked(val counter: Counter) : CounterListUiEvents()
    data class OnMinusClicked(val counter: Counter) : CounterListUiEvents()
    data class OnStepsSet(val steps: Int, val counter: Counter) : CounterListUiEvents()
    data class OnTargetSet(val target: Int, val counter: Counter) : CounterListUiEvents()
    data object OnMenuClicked : CounterListUiEvents()
    data class OnNameChanged(val counter: Counter, val name: String) : CounterListUiEvents()
    data object OnBackPressed : CounterListUiEvents()
}