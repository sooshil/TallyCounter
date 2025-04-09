package com.sukajee.counter.presentation.counter_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sukajee.counter.presentation.counter_list.CounterListUiEvents

@ExperimentalMaterial3Api
@Composable
fun CounterDetailsRoot(
    onBackClicked: (CounterListUiEvents) -> Unit,
    counterId: Int?,
    viewModel: CounterDetailsViewModel
) {
    counterId?.let {
        LaunchedEffect(counterId) {
            viewModel.getCounter(it)
        }
    }
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    CounterDetails(
        state = state,
        onEvent = {
            if (it is CounterListUiEvents.OnBackPressed) {
                onBackClicked(it)
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun CounterDetails(
    onEvent: (CounterListUiEvents) -> Unit,
    state: CounterDetailsUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.counterName)
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(CounterListUiEvents.OnBackPressed)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back to previous screen"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = state.currentCount.toString())
        }
    }
}