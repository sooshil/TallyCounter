package com.sukajee.tallycounter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sukajee.counter.presentation.counter_details.MainScreenRoot
import com.sukajee.counter.presentation.counter_details.MainViewModel

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
            val viewModel = viewModel<MainViewModel>()
            MainScreenRoot(
                viewModel = viewModel
            )
        }
    }
}