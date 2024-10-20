package com.example.lab8.layouts.screen6

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

object ProfileDestination {
    const val route = "profile"
}

fun NavController.navigateToProfileScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(ProfileDestination.route, navOptions)
}

fun NavGraphBuilder.profileScreen(
    onBackToLogin: () -> Unit
) {
    composable(ProfileDestination.route) {
        ProfileRoute(onBackClick = onBackToLogin)
    }
}
