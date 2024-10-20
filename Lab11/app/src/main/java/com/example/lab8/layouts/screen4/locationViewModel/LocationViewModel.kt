package com.example.lab8.layouts.screen4.locationViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.lab8.layouts.data.local.domain.Location
import com.example.lab8.layouts.data.local.domain.LocationDb
import kotlin.random.Random

data class LocationUiState(
    val isLoading: Boolean = true,
    val data: List<Location>? = null,
    val hasError: Boolean = false
)

class LocationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            _uiState.value = LocationUiState(isLoading = true)
            delay(4000)

            val randomValue = Random.nextInt(100)
            if (randomValue < 25) {
                _uiState.value = LocationUiState(isLoading = false, hasError = true)
            } else {
                val locations = LocationDb().getAllLocations()
                _uiState.value = LocationUiState(isLoading = false, data = locations)
            }
        }
    }

    fun retryLoadLocations() {
        loadLocations()
    }

    fun triggerError() {
        _uiState.value = _uiState.value.copy(hasError = true)
    }
}
