package com.sukajee.tallycounter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sukajee.tallycounter.navigation.Navigation
import com.sukajee.core.ui.theme.TallyCounterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallyCounterTheme {
                Navigation()
            }
        }
    }
}
