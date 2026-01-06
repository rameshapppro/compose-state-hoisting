package com.state.hoisting.ui.state

import androidx.compose.runtime.Immutable

@Immutable
data class CounterUiState(
    val count: Int = 0,
    val isEven: Boolean = true
)