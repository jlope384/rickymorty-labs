package com.example.lab8.layouts.screen2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.example.lab8.layouts.data.Character
import com.example.lab8.layouts.data.CharacterDb

// Ruta principal que maneja la lista de personajes
@Composable
fun CharacterRoute(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Se usa `remember` para que la lista de personajes se mantenga entre recomposiciones
    val characters by remember { mutableStateOf(CharacterDb().getAllCharacters()) }

    // Pantalla principal de la lista de personajes
    CharacterListScreen(
        characters = characters,
        onCharacterClick = onCharacterClick,
        modifier = modifier
    )
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
                    containerColor = MaterialTheme.colorScheme.primary // Usa el color primario del tema
                )
            )
        }
    ) { paddingValues ->
        // LazyColumn que muestra la lista de personajes con relleno adecuado
        LazyColumn(
            contentPadding = paddingValues,
            modifier = modifier.fillMaxSize()
        ) {
            items(characters.size) { index ->
                CharacterListItem(
                    character = characters[index],
                    onClick = onCharacterClick
                )
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
            .padding(8.dp)
    ) {
        // Imagen del personaje usando Coil
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
        Spacer(modifier = Modifier.width(8.dp))

        // Información del personaje (nombre y especie)
        Column {
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = character.species,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

