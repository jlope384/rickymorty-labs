package com.example.lab8.layouts.screen3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lab8.layouts.data.Character
import com.example.lab8.layouts.utilScreens.ErrorScreen
import com.example.lab8.layouts.utilScreens.LoadingScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab8.layouts.screen3.characterDetailViewModel.CharacterDetailViewModel

@Composable
fun CharacterProfileRoute(
    characterId: Int,
    onNavigateBack: () -> Unit,
    viewModel: CharacterDetailViewModel = viewModel()
) {
    // Cargar detalles del personaje en el ViewModel
    viewModel.loadCharacterDetail(characterId)

    // Obtener el estado del ViewModel
    val uiState = viewModel.uiState.collectAsState().value

    when {
        uiState.isLoading -> LoadingScreen(onClick = { viewModel.triggerError() })
        uiState.hasError -> ErrorScreen(onRetry = { viewModel.retryLoadCharacter(characterId) })
        uiState.data != null -> {
            CharacterDetailScreen(
                character = uiState.data,
                onBackClick = onNavigateBack
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: Character,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CharacterImage(character.image)
            Spacer(modifier = Modifier.height(16.dp))
            CharacterInfoRow(label = "Name", info = character.name)
            CharacterInfoRow(label = "Species", info = character.species)
            CharacterInfoRow(label = "Status", info = character.status)
            CharacterInfoRow(label = "Gender", info = character.gender)
        }
    }
}

@Composable
fun CharacterImage(imageUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
            .padding(8.dp)
    )
}

@Composable
fun CharacterInfoRow(label: String, info: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = "$label:", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = info, style = MaterialTheme.typography.bodyLarge)
    }
}
