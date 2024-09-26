package com.example.lab8.layouts.screen4

import androidx.navigation.*
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LocationDestination

fun NavController.navigateToLocationScreen(
    destination: LocationDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationScreen(
    onBackToLogin: () -> Unit
) {
    composable<LocationDestination> {
        //LocationRoute(
            //onBackToLogin = onBackToLogin
        //)
    }
}