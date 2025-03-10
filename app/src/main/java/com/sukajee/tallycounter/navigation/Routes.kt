package com.sukajee.tallycounter.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object CounterListScreen: Routes

    @Serializable
    data class CounterDetailScreen(val counterId: Int): Routes
}
