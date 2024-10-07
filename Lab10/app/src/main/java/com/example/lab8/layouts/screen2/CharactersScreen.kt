package com.example.lab8.layouts.screen2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lab8.layouts.data.Character
import com.example.lab8.layouts.screen2.characterViewModel.CharacterViewModel
import com.example.lab8.layouts.utilScreens.ErrorScreen
import com.example.lab8.layouts.utilScreens.LoadingScreen

@Composable
fun CharacterRoute(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            LoadingScreen(onClick = { viewModel.triggerError() })
        }
        uiState.hasError -> {
            ErrorScreen(onRetry = { viewModel.retryLoadCharacters() })
        }
        uiState.data != null -> {
            CharacterListScreen(
                characters = uiState.data!!,
                onCharacterClick = onCharacterClick,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Characters") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = modifier.fillMaxSize()
        ) {
            items(characters) { character ->
                CharacterListItem(character = character, onClick = onCharacterClick)
            }
        }
    }
}

@Composable
fun CharacterListItem(character: Character, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(character.id) }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.image)
                .crossfade(true)
                .build(),
            contentDescription = "${character.name} Image",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = character.name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
