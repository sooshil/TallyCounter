package com.sukajee.counter.presentation.counter_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sukajee.counter.domain.Counter

@Composable
fun CountersListRoot(
    viewModel: CounterViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(CounterListUiEvents.OnAddCounterClicked)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add counter"
                    )
                }
            )
        }
    ) {
        CountersListScreen(
            state = state.value,
            onEvent = viewModel::onEvent,
            modifier = modifier.padding(it)
        )
    }
}

@Composable
fun CountersListScreen(
    state: CounterListUiState,
    onEvent: (CounterListUiEvents) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        state = rememberLazyListState(),
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = state.counters,
            key = { it.id }
        ) {
            CounterItem(
                counter = it,
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun CounterItem(
    counter: Counter,
    onEvent: (CounterListUiEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            IconButton(
                modifier = Modifier.fillMaxSize(),
                onClick = {
                    onEvent(
                        CounterListUiEvents.OnPlusClicked(
                            counter = counter
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase counter.",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        Text(
            text = counter.currentCount.toString(),
            style = MaterialTheme.typography.headlineLarge
        )

        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            IconButton(
                modifier = Modifier.fillMaxSize(),
                onClick = {
                    onEvent(
                        CounterListUiEvents.OnMinusClicked(
                            counter = counter
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Increase counter.",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
