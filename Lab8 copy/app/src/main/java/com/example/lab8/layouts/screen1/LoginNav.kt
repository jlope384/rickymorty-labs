package com.example.lab8.layouts.screen1

import LoginRoute
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.uvg.javier.Screen3.CharacterDetailDestination


@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
    ) {
    composable<LoginDestination> {
        LoginRoute(onLoginClick = onLoginClick, modifier = Modifier.fillMaxWidth())
    }
}

