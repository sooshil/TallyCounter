package com.sukajee.counter.presentation.counter_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.OnResetButtonClick -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentCount = 0,
                        target = Int.MAX_VALUE,
                        steps = 1
                    )
                }
            }
            MainUiEvent.OnStepsButtonClick -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        steps = 2
                    )
                }
            }
            MainUiEvent.OnTargetButtonClick -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        target = 50
                    )
                }
            }

            MainUiEvent.OnMinusButtonClick -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentCount = currentState.currentCount - currentState.steps
                    )
                }
            }
            MainUiEvent.OnPlusButtonClick -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentCount = currentState.currentCount + currentState.steps
                    )
                }
            }
        }
    }
}