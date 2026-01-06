package com.state.hoisting.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.state.hoisting.ui.state.CounterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState

    fun increment() {
        _uiState.update { currentState ->
            val newCount = currentState.count + 1
            currentState.copy(
                count = newCount,
                isEven = newCount % 2 == 0
            )
        }
    }
}