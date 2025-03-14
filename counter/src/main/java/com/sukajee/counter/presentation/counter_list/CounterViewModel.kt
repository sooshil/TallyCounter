package com.sukajee.counter.presentation.counter_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukajee.counter.domain.Counter
import com.sukajee.counter.domain.CounterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(
    private val repository: CounterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CounterListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllCounters()
                .collect { counters ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            counters = counters
                        )
                    }
                }
        }
    }

    fun onEvent(event: CounterListUiEvents) {
        when (event) {
            CounterListUiEvents.OnAddCounterClicked -> {
                val counter = Counter(
                    name = "Counter",
                    currentCount = 0,
                    target = Int.MAX_VALUE,
                    steps = 1,
                    isPinned = false
                )
                viewModelScope.launch {
                    repository.upsertCounter(counter)
                }
            }

            is CounterListUiEvents.OnCounterClicked -> {

            }

            is CounterListUiEvents.OnDeleteCounterClicked -> {
                viewModelScope.launch {
                    repository.deleteCounterById(event.counter.id)
                }
            }

            is CounterListUiEvents.OnPinCounterClicked -> {
                viewModelScope.launch {
                    repository.upsertCounter(
                        event.counter.copy(
                            isPinned = !event.counter.isPinned
                        )
                    )
                }
            }

            is CounterListUiEvents.OnStepsSet -> {
                viewModelScope.launch {
                    repository.upsertCounter(
                        counter = event.counter.copy(steps = event.steps)
                    )
                }
            }

            is CounterListUiEvents.OnTargetSet -> {
                viewModelScope.launch {
                    repository.upsertCounter(
                        counter = event.counter.copy(target = event.target)
                    )
                }
            }

            is CounterListUiEvents.OnMinusClicked -> {
                viewModelScope.launch {
                    repository.updateCounter(
                        counterId = event.counter.id,
                        currentCount = event.counter.currentCount - event.counter.steps
                    )
                }
            }

            is CounterListUiEvents.OnPlusClicked -> {
                viewModelScope.launch {
                    repository.updateCounter(
                        counterId = event.counter.id,
                        currentCount = event.counter.currentCount + event.counter.steps
                    )
                }
            }
        }
    }
}