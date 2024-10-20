package com.example.lab8.layouts.screen3.characterDetailViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.lab8.layouts.data.local.domain.Character
import com.example.lab8.layouts.data.local.domain.CharacterDb

data class CharacterDetailUiState(
    val isLoading: Boolean = true,
    val data: Character? = null,
    val hasError: Boolean = false
)

class CharacterDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState

    fun loadCharacterDetail(characterId: Int) {
        viewModelScope.launch {
            _uiState.value = CharacterDetailUiState(isLoading = true)
            delay(2000)

            val character = CharacterDb().getCharacterById(characterId)
            _uiState.value = CharacterDetailUiState(isLoading = false, data = character)
        }
    }

    fun retryLoadCharacter(characterId: Int) {
        loadCharacterDetail(characterId)
    }

    fun triggerError() {
        _uiState.value = _uiState.value.copy(hasError = true)
    }
}
