package com.example.lab8.layouts.screen5

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailDestination(val locationId: Int)

fun NavController.navigateToLocationDetailScreen(
    locationId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate("locationDetail/$locationId", navOptions)
}

fun NavGraphBuilder.locationDetailScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "locationDetail/{locationId}",
        arguments = listOf(navArgument("locationId") { type = NavType.IntType })
    ) { backStackEntry ->
        val locationId = backStackEntry.arguments?.getInt("locationId") ?: return@composable
        LocationDetRoute(locationId = locationId, onBackClick = onBackClick)
    }
}
