package com.example.lab8.layouts.screen2.characterViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.lab8.layouts.data.Character
import com.example.lab8.layouts.data.CharacterDb
import kotlin.random.Random

data class CharacterUiState(
    val isLoading: Boolean = true,
    val data: List<Character>? = null,
    val hasError: Boolean = false
)

class CharacterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState: StateFlow<CharacterUiState> = _uiState

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterUiState(isLoading = true)
            delay(4000)

            val randomValue = Random.nextInt(100)

            if (randomValue < 25) {
                _uiState.value = CharacterUiState(isLoading = false, hasError = true)
            } else {
                val characters = CharacterDb().getAllCharacters()
                _uiState.value = CharacterUiState(isLoading = false, data = characters)
            }
        }
    }

    fun retryLoadCharacters() {
        loadCharacters()
    }

    fun triggerError() {
        _uiState.value = _uiState.value.copy(hasError = true)
    }
}