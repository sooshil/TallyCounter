package com.sukajee.counter.presentation.counter_details

sealed class MainUiEvent {
    data object OnResetButtonClick : MainUiEvent()
    data object OnTargetButtonClick : MainUiEvent()
    data object OnStepsButtonClick : MainUiEvent()
    data object OnPlusButtonClick : MainUiEvent()
    data object OnMinusButtonClick : MainUiEvent()
}