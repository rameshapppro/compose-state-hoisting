package com.state.hoisting.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.state.hoisting.Spacing
import com.state.hoisting.ui.components.PrimaryButton
import com.state.hoisting.ui.state.CounterUiState

@Composable
fun CounterScreen(paddingValues: PaddingValues,uiState: CounterUiState, onIncrement: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = "Count: ${uiState.count}")
        Spacer(modifier = Modifier.height(Spacing.lg))
        PrimaryButton(text = "Increment", onClick = onIncrement)
    }
}