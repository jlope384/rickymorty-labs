package com.example.lab8.layouts.screen6

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavController.navigateToProfileScreen(
    destination: ProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.profileScreen(
    onBackToLogin: () -> Unit
) {
    composable<ProfileDestination> {
        //ProfileRoute(
            //onBackToLogin = onBackToLogin
        //)
    }
}