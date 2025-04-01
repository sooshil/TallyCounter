package com.sukajee.tallycounter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sukajee.counter.presentation.counter_list.CounterViewModel
import com.sukajee.counter.presentation.counter_list.CountersListRoot
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        startDestination = Routes.CounterListScreen,
        navController = navController
    ) {
        composable<Routes.CounterListScreen> {
            val viewModel = koinViewModel<CounterViewModel>()
            CountersListRoot(
                viewModel = viewModel
            )
        }
    }
}