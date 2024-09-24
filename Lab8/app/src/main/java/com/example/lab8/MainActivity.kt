package com.example.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.lab8.layouts.screen1.LoginDestination
import com.example.lab8.layouts.screen1.loginScreen
import com.example.lab8.layouts.screen2.CharacterDestination
import com.example.lab8.layouts.screen2.characterScreen
import com.example.lab8.layouts.screen2.navigateToCharacterScreen
import com.example.lab8.ui.theme.Lab8Theme
import com.uvg.javier.Screen3.characterDetailScreen
import com.uvg.javier.Screen3.navigateToCharacterDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab8Theme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = LoginDestination
                    ) {
                        loginScreen(
                            onLoginClick = {
                                navController.navigateToCharacterScreen(CharacterDestination)
                            }
                        )

                        characterScreen(
                            onCharacterClick = { characterId ->
                                navController.navigateToCharacterDetailScreen(characterId)
                            },
                            onBackToLogin = {
                                navController.popBackStack(LoginDestination, inclusive = false)
                            }
                        )

                        characterDetailScreen(
                            onNavigateBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}
