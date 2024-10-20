// navigateToLocationScreen.kt
package com.example.lab8.layouts.screen4

import androidx.navigation.*
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LocationDestination

fun NavController.navigateToLocationScreen(
    navOptions: NavOptions? = null
) {
    this.navigate("locations", navOptions)
}

fun NavGraphBuilder.locationScreen(
    onLocationClick: (Int) -> Unit
) {
    composable("locations") {
        LocationRoute(
            onLocationClick = onLocationClick
        )
    }
}
