package com.sukajee.counter.presentation.counter_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukajee.core.ui.theme.TallyCounterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample(
    modifier: Modifier = Modifier
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val sheetState = rememberModalBottomSheetState()
        var isSheetOpen by rememberSaveable {
            mutableStateOf(false)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = {
                    isSheetOpen = true
                }
            ) {
                Text(text = "Open Sheet")

            }
        }
        if (isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isSheetOpen = false
                }
            ) {
                Surface(
                    shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Column {
                            Text(
                                text = "FIFTH THIRD MOMENTUM CHECKING x2345",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color(0xC60337F4)
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Surface(
                    shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Surface(
                        shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.background
                    )
                    {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = Color(0xC60337F4)
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "Transfer Funds",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold, color = Color(0xC60337F4)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color(0xC60337F4),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Surface(
                    shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Surface(
                        shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = Color(0xC60337F4)
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "Send Money with Zelle@",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold, color = Color(0xC60337F4)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color(0xC60337F4),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Surface(
                    shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Surface(
                        shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.background
                    )
                    {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = Color(0xC60337F4)
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "Manage Card",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold, color = Color(0xC60337F4)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color(0xC60337F4),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Surface(
                    shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Surface(
                        shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = Color(0xC60337F4)
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = "View Documents",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold, color = Color(0xC60337F4)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color(0xC60337F4),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SamplePreview() {
    TallyCounterTheme {
        Sample()
    }
}