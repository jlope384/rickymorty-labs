package com.example.lab8.layouts.screen5

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab8.layouts.data.LocationDb

@Composable
fun LocationDetRoute() {
    LocationDetScreen(onBackClick = {}, locationId = 1)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetScreen(onBackClick: () -> Unit, locationId: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Location Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
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
            val location = LocationDb().getLocationById(locationId)
            if (location != null) {
                Text(text = "Name: ${location.name}")
                Text(text = "Type: ${location.type}")
                Text(text = "Dimension: ${location.dimension}")
            } else {
                Text("Location not found")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationDetScreen() {
    LocationDetScreen(onBackClick = {}, locationId = 1)
}
