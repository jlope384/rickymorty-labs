package com.example.lab8.layouts.screen4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab8.layouts.data.Location
import com.example.lab8.layouts.data.LocationDb

private val locationDb = LocationDb()

@Composable
fun LocationRoute(
    onLocationClick: (Int) -> Unit
) {
    LocationScreen(
        onLocationClick = onLocationClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(
    onLocationClick: (Int) -> Unit
) {
    val locations = locationDb.getAllLocations()

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
            items(locations) { location ->
                LocationItem(location = location, onLocationClick = onLocationClick)
                Spacer(modifier = Modifier.height(10.dp))
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
    LocationScreen(
        onLocationClick = {}
    )
}
