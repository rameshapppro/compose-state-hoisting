package com.state.hoisting.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.state.hoisting.Spacing
import com.state.hoisting.ui.components.PrimaryButton
import com.state.hoisting.ui.state.CounterUiState

@Composable
fun CounterScreen(uiState: CounterUiState, onIncrement: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Count: ${uiState.count}",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = if (uiState.isEven) "Even Number" else "Odd Number",
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Increment",
            onClick = onIncrement
        )
    }
}