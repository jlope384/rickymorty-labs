package com.example.lab8.layouts.screen2

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CharacterDestination


fun NavController.navigateToCharacterScreen(
    destination: CharacterDestination,
    navOptions: NavOptions? = null
)
{
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterScreen(
    onCharacterClick: (Int) -> Unit,
    onBackToLogin: () -> Unit
) {
    composable<CharacterDestination> {
        CharacterRoute(
            onCharacterClick = onCharacterClick,
            modifier = Modifier.fillMaxWidth()
        )

        BackHandler {
            onBackToLogin()
        }
    }
}
