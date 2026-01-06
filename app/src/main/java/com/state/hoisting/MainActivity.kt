package com.state.hoisting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.state.hoisting.ui.screens.CounterScreen
import com.state.hoisting.ui.theme.StateHoistingTheme
import com.state.hoisting.ui.viewmodel.CounterViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateHoistingTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                CounterScreen(
                    uiState = uiState,
                    onIncrement = viewModel::increment
                )
            }
        }
    }
}