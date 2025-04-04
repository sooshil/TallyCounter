@file:OptIn(ExperimentalMaterial3Api::class)

package com.sukajee.counter.presentation.counter_list

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sukajee.core.ui.theme.TallyCounterTheme
import com.sukajee.counter.domain.Counter

@Composable
fun CountersListRoot(
    viewModel: CounterViewModel,
    onCounterClicked: (counterId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    CountersListScreen(
        state = state.value,
        onEvent = { event ->
            if (event is CounterListUiEvents.OnCounterClicked) {
                onCounterClicked(event.counterId)
            }
            viewModel.onEvent(event)
        },
        modifier = modifier
    )
}

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
                            onEvent(CounterListUiEvents.OnMenuClicked)
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
    ) { paddingValues ->
        val lazyListState = rememberLazyListState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            LaunchedEffect(state.counters.size) {
                lazyListState.animateScrollToItem(0)
            }

            var editingCounterId by remember {
                mutableStateOf<Int?>(null)
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                state = lazyListState,
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = state.counters,
                    key = { counter -> counter.id }
                ) { counter ->
                    CounterItem(
                        modifier = Modifier
                            .animateItem(),
                        counter = counter,
                        isEditing = editingCounterId == counter.id,
                        onStartEditing = {
                            editingCounterId = counter.id
                        },
                        onEvent = {
                            if (it is CounterListUiEvents.OnNameChanged) {
                                editingCounterId = null
                            }
                            onEvent(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CounterItem(
    counter: Counter,
    modifier: Modifier = Modifier,
    isEditing: Boolean = false,
    onStartEditing: () -> Unit = {},
    onEvent: (CounterListUiEvents) -> Unit
) {
    val focusManager = LocalFocusManager.current // To clear focus
    val focusRequester = remember {
        FocusRequester()
    }

    var textFieldValue by remember(isEditing) {
        mutableStateOf(
            TextFieldValue(
                text = counter.name,
                selection = TextRange(counter.name.length)
            )
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            if (isEditing.not()) {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = counter.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable {
                                onStartEditing()
                            }
                            .weight(1f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    CardButton(
                        modifier = modifier
                            .size(24.dp),
                        onClick = {
                            onEvent(
                                CounterListUiEvents.OnCounterClicked(
                                    counterId = counter.id
                                )
                            )
                        },
                        icon = Icons.Default.Fullscreen,
                        contentDescription = "Open this counter in full screen",
                    )
                }
                Spacer(Modifier.height(13.dp))
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = textFieldValue,
                        onValueChange = { newValue ->
                            textFieldValue = newValue.copy(
                                selection = TextRange(newValue.text.length)
                            )
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onEvent(
                                    CounterListUiEvents.OnNameChanged(
                                        counter = counter,
                                        name = textFieldValue.text
                                    )
                                )
                                focusManager.clearFocus()
                            }
                        ),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    onEvent(
                                        CounterListUiEvents.OnNameChanged(
                                            counter = counter, name = textFieldValue.text
                                        )
                                    )
                                    focusManager.clearFocus()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Save",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                    )
                }

                LaunchedEffect(isEditing) {
                    if (isEditing) {
                        focusRequester.requestFocus()
                    }
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardButton(
                    modifier = modifier,
                    onClick = {
                        onEvent(
                            CounterListUiEvents.OnMinusClicked(
                                counter = counter
                            )
                        )
                    },
                    icon = Icons.Default.Remove,
                    contentDescription = "Decrease counter"
                )

                Text(
                    text = counter.currentCount.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 44.sp
                )

                CardButton(
                    modifier = modifier,
                    onClick = {
                        onEvent(
                            CounterListUiEvents.OnPlusClicked(
                                counter = counter
                            )
                        )
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Increase counter"
                )
            }
        }
    }
}

@Composable
private fun CardButton(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = CircleShape
            )
            .size(70.dp)
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
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
