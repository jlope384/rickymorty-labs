package com.example.lab8.layouts.screen5.locationDetViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8.layouts.data.Location
import com.example.lab8.layouts.data.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

// Estado de la UI para los detalles de la ubicación
data class LocationDetailUiState(
    val isLoading: Boolean = true,
    val data: Location? = null,
    val hasError: Boolean = false
)

class LocationDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LocationDetailUiState())
    val uiState: StateFlow<LocationDetailUiState> = _uiState


    fun loadLocationDetails(locationId: Int) {
        viewModelScope.launch {
            _uiState.value = LocationDetailUiState(isLoading = true)

            delay(2000) // Simulamos un retraso para la carga de datos

            // Simular un 25% de probabilidad de error
            val randomChance = Random.nextInt(0, 100)
            if (randomChance < 25) {
                _uiState.value = _uiState.value.copy(hasError = true, isLoading = false)
            } else {
                val location = LocationDb().getLocationById(locationId)
                _uiState.value = LocationDetailUiState(
                    isLoading = false,
                    data = location,
                    hasError = false
                )
            }
        }
    }

    fun retryLoading(locationId: Int) {
        loadLocationDetails(locationId)
    }
}
