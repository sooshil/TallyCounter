@file:OptIn(ExperimentalMaterial3Api::class)

package com.sukajee.counter.presentation.counter_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sukajee.core.ui.theme.TallyCounterTheme
import com.sukajee.counter.domain.Counter

@Composable
fun CountersListRoot(
    viewModel: CounterViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    CountersListScreen(
        state = state.value,
        onEvent = viewModel::onEvent,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountersListScreen(
    state: CounterListUiState,
    onEvent: (CounterListUiEvents) -> Unit,
    modifier: Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(CounterListUiEvents.OnAddCounterClicked)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add counter"
                    )
                }
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Counters")
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Options"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(CounterListUiEvents.OnAddCounterClicked)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Add counter"
                        )
                    }
                }
            )
        }
    ) {
        val lazyListState = rememberLazyListState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            LaunchedEffect(state.counters.size) {
                lazyListState.animateScrollToItem(0)
            }
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(
                    items = state.counters,
                    key = { counter -> counter.id }
                ) { counter ->
                    CounterItem(
                        modifier = Modifier
                            .animateItem(),
                        counter = counter,
                        onEvent = onEvent
                    )
                }
            }
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
    }
}

@PreviewLightDark
@Composable
fun CounterListScreenPreview() {
    TallyCounterTheme {
        CountersListScreen(
            state = CounterListUiState(
                counters = listOf(
                    Counter(
                        name = "Counter",
                        currentCount = 0,
                        target = Int.MAX_VALUE,
                        steps = 1,
                        isPinned = false,
                        id = 4
                    ),
                ),
                isLoading = false
            ),
            onEvent = {},
            modifier = Modifier
        )
    }
}
