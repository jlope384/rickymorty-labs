package com.uvg.javier.Screen3

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lab8.layouts.screen3.CharacterProfileRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailDestination(val characterId: Int)

fun NavController.navigateToCharacterDetailScreen(
    characterId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate("characterDetail/$characterId", navOptions)
}

fun NavGraphBuilder.characterDetailScreen(onNavigateBack: () -> Unit) {
    composable(
        route = "characterDetail/{characterId}",
        arguments = listOf(navArgument("characterId") { type = NavType.IntType })
    ) { backStackEntry ->
        val characterId = backStackEntry.arguments?.getInt("characterId")
        if (characterId != null) {
            CharacterProfileRoute(
                characterId = characterId,
                onNavigateBack = onNavigateBack
            )
        }
    }
}
