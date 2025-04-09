package com.sukajee.tallycounter.navigation

import kotlinx.serialization.Serializable


@Serializable
data object CounterListScreen

@Serializable
data class CounterDetailScreen(val counterId: Int)
