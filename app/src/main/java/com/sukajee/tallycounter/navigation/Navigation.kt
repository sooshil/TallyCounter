package com.sukajee.tallycounter.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sukajee.counter.presentation.counter_details.CounterDetailsRoot
import com.sukajee.counter.presentation.counter_details.CounterDetailsViewModel
import com.sukajee.counter.presentation.counter_list.CounterViewModel
import com.sukajee.counter.presentation.counter_list.CountersListRoot
import org.koin.compose.viewmodel.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        startDestination = CounterListScreen,
        navController = navController,
        enterTransition = { NavAnimation.enter },
        exitTransition = { NavAnimation.exit },
        popEnterTransition = { NavAnimation.popEnter },
        popExitTransition = { NavAnimation.popExit }
    ) {
        composable<CounterListScreen> {
            val viewModel = koinViewModel<CounterViewModel>()
            CountersListRoot(
                viewModel = viewModel,
                onCounterClicked = { counterId ->
                    navController.navigate(
                        CounterDetailScreen(
                            counterId = counterId
                        )
                    )
                }
            )
        }

        composable<CounterDetailScreen> {
            val counterId = it.toRoute<CounterDetailScreen>().counterId
            val viewModel = koinViewModel<CounterDetailsViewModel>()
            CounterDetailsRoot(
                counterId = counterId,
                viewModel = viewModel,
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    }
}

object NavAnimation {
    val enter = slideInHorizontally(
        animationSpec = tween(durationMillis = 500),
        initialOffsetX = { it }
    ) /*+ fadeIn(animationSpec = fadeAnimation)*/
    val exit = slideOutHorizontally(
        animationSpec = tween(durationMillis = 500),
        targetOffsetX = { -it }
    ) /*+ fadeIn(animationSpec = fadeAnimation)*/
    val popEnter = slideInHorizontally(
        animationSpec = tween(durationMillis = 500),
        initialOffsetX = { -it }
    ) /*+ fadeIn(animationSpec = fadeAnimation)*/
    val popExit = slideOutHorizontally(
        animationSpec = tween(durationMillis = 500),
        targetOffsetX = { it }
    ) /*+ fadeIn(animationSpec = fadeAnimation)*/
}