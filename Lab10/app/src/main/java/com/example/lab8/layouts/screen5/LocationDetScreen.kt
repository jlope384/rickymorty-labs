package com.example.lab8.layouts.screen5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab8.layouts.screen5.locationDetViewModel.LocationDetailViewModel
import com.example.lab8.layouts.utilScreens.ErrorScreen
import com.example.lab8.layouts.utilScreens.LoadingScreen

@Composable
fun LocationDetRoute(
    locationId: Int,
    onBackClick: () -> Unit,
    viewModel: LocationDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLocationDetails(locationId)
    }

    when {
        uiState.isLoading -> {
            LoadingScreen(onClick = { viewModel.retryLoading(locationId) })
        }
        uiState.hasError -> {
            ErrorScreen(onRetry = { viewModel.retryLoading(locationId) })
        }
        uiState.data != null -> {
            LocationDetScreen(onBackClick = onBackClick, location = uiState.data!!)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetScreen(onBackClick: () -> Unit, location: com.example.lab8.layouts.data.Location) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Location Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contenedor que centra los detalles verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra el contenido verticalmente
        ) {
            Text(text = "Name: ${location.name}")
            Text(text = "Type: ${location.type}")
            Text(text = "Dimension: ${location.dimension}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationDetScreen() {
    LocationDetScreen(
        onBackClick = {},
        location = com.example.lab8.layouts.data.Location(
            id = 1,
            name = "Earth",
            type = "Planet",
            dimension = "Dimension C-137"
        )
    )
}
