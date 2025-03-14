package com.sukajee.tallycounter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sukajee.counter.presentation.counter_list.CounterViewModel
import com.sukajee.counter.presentation.counter_list.CountersListRoot
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(
        startDestination = Routes.CounterListScreen,
        navController = navController,
        modifier = modifier
    ) {
        composable<Routes.CounterListScreen> {
            val viewModel = koinViewModel<CounterViewModel>()
            CountersListRoot(
                viewModel = viewModel
            )
        }
    }
}