package com.example.lab8.layouts.screen4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab8.layouts.data.Location
import com.example.lab8.layouts.screen4.locationViewModel.LocationViewModel
import com.example.lab8.layouts.utilScreens.ErrorScreen
import com.example.lab8.layouts.utilScreens.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationRoute(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationViewModel = viewModel() // Instancia del ViewModel
) {
    // Obtener el estado del ViewModel
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> LoadingScreen(onClick = { viewModel.triggerError() }) // Manejo de carga
        uiState.hasError -> ErrorScreen(onRetry = { viewModel.retryLoadLocations() }) // Manejo de error
        uiState.data != null -> {
            // Si hay datos, muestra la lista de ubicaciones
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(
                    title = { Text("Locations") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    items(uiState.data!!) { location ->
                        LocationItem(location = location, onLocationClick = onLocationClick)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun LocationItem(location: Location, onLocationClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onLocationClick(location.id) }
            .padding(8.dp)
    ) {
        Text(text = location.name, style = TextStyle(fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = location.type, style = TextStyle(fontSize = 14.sp))
        Text(text = location.dimension, style = TextStyle(fontSize = 12.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationScreen() {
    LocationRoute(
        onLocationClick = {}
    )
}