package com.sukajee.counter.presentation.counter_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukajee.counter.domain.Counter
import com.sukajee.counter.domain.CounterRepository
import com.sukajee.counter.presentation.counter_list.CounterListUiEvents
import com.sukajee.counter.presentation.counter_list.CounterListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterDetailsViewModel(
    private val repository: CounterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CounterDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getCounter(counterId: Int) {
        viewModelScope.launch {
            val counter = repository.getCounter(counterId)
            counter?.let {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentCount = it.currentCount,
                        isLoading = false,
                        currentStep = it.steps,
                        currentTarget = it.target,
                        counterName = it.name
                    )
                }
            }
        }
    }

    fun onEvent(event: CounterDetailsUiEvents) {
        when (event) {
            CounterDetailsUiEvents.OnAddCounterClicked -> TODO()
        }
    }
}