package com.state.hoisting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.state.hoisting.ui.screens.CounterScreen
import com.state.hoisting.ui.theme.StateHoistingTheme
import com.state.hoisting.ui.viewmodel.CounterViewModel

class AppActivity : ComponentActivity() {
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateHoistingTheme {
                val uiState by viewModel.uiState.collectAsState()

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayLarge.copy(fontSize = 40.sp),
                            text = "STATE HOISTING"
                        )
                    },
                    modifier = Modifier.fillMaxSize().padding(top = 50.dp)
                ) { paddingValues ->
                    CounterScreen(
                        uiState = uiState,
                        onIncrement = viewModel::increment,
                        paddingValues = paddingValues
                    )
                }


            }
        }
    }
}