package com.sukajee.counter.presentation.counter_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sukajee.core.ui.theme.TallyCounterTheme

@Composable
fun MainScreenRoot(
    viewModel: MainViewModel,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { innerPadding ->
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        MainScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun MainScreen(
    state: UiState,
    onEvent: (MainUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0E1344))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .size(150.dp)
                    .padding(16.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.Magenta),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            onEvent(MainUiEvent.OnPlusButtonClick)
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color(0xFF0D1936)
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF08122D),
                    contentColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.target == Int.MAX_VALUE) {
                        Text(
                            text = state.currentCount.toString(),
                            fontSize = 100.sp
                        )
                    } else {
                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 100.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            ) {
                                append(state.currentCount.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White.copy(0.5f),
                                    fontSize = 20.sp
                                )
                            ) {
                                append("/${state.target}")
                            }
                        }
                        Text(
                            text = annotatedString,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .size(150.dp)
                    .padding(16.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.Magenta),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            onEvent(MainUiEvent.OnMinusButtonClick)
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Add",
                            tint = Color(0xFF0D1936)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    onEvent(MainUiEvent.OnResetButtonClick)
                }
            ) {
                Text(text = "Reset")
            }

            Button(
                onClick = {
                    onEvent(MainUiEvent.OnTargetButtonClick)
                }
            ) {
                Text(text = "Target")
            }

            Button(
                onClick = {
                    onEvent(MainUiEvent.OnStepsButtonClick)
                }
            ) {
                Text(text = "Steps")
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    TallyCounterTheme {
        MainScreen(
            state = UiState(
                currentCount = 0,
                target = 52
            ),
            onEvent = {}
        )
    }
}