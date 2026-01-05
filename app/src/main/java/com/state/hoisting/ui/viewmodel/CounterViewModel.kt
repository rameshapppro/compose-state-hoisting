package com.state.hoisting.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.state.hoisting.ui.state.CounterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CounterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState

    fun increment() {
        _uiState.value = _uiState.value.copy(count = _uiState.value.count + 1)
    }
}