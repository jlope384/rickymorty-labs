package com.example.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.lab8.layouts.BottomBar.BottomBar
import com.example.lab8.layouts.screen1.LoginDestination
import com.example.lab8.layouts.screen1.loginScreen
import com.example.lab8.layouts.screen2.CharacterDestination
import com.example.lab8.layouts.screen2.characterScreen
import com.example.lab8.layouts.screen2.navigateToCharacterScreen
import com.example.lab8.layouts.screen5.LocationDetailDestination
import com.example.lab8.layouts.screen5.locationDetailScreen
import com.example.lab8.layouts.screen5.navigateToLocationDetailScreen
import com.example.lab8.layouts.screen6.ProfileDestination
import com.example.lab8.layouts.screen6.profileScreen
import com.example.lab8.layouts.screen6.navigateToProfileScreen
import com.example.lab8.ui.theme.Lab8Theme
import com.uvg.javier.Screen3.characterDetailScreen
import com.uvg.javier.Screen3.navigateToCharacterDetailScreen
import com.example.lab8.layouts.BottomBar.bottomBarItems
import com.example.lab8.layouts.screen4.LocationDestination
import com.example.lab8.layouts.screen4.navigateToLocationScreen
import com.example.lab8.layouts.screen4.locationScreen


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
                    var loggedIn by remember { mutableStateOf(false) }
                    var selectedItemIndex by remember { mutableStateOf(0) }

                    Scaffold(
                        modifier = Modifier,
                        bottomBar = {
                            if (loggedIn) {
                                BottomBar(
                                    navController = navController,
                                    selectedItemIndex = selectedItemIndex,
                                    onItemSelected = { index ->
                                        selectedItemIndex = index
                                        when (bottomBarItems[index].route) {
                                            "characters" -> {
                                                navController.navigateToCharacterScreen(CharacterDestination)
                                            }
                                            "locations" -> { // Asegúrate que esta ruta coincida
                                                navController.navigateToLocationScreen()
                                            }
                                            "profile" -> {
                                                navController.navigateToProfileScreen()
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = LoginDestination,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            loginScreen(
                                onLoginClick = {
                                    loggedIn = true
                                    navController.navigateToCharacterScreen(CharacterDestination)
                                }
                            )

                            characterScreen(
                                onCharacterClick = { characterId ->
                                    navController.navigateToCharacterDetailScreen(characterId)
                                },
                                onBackToLogin = {
                                    loggedIn = false
                                    navController.popBackStack(LoginDestination, inclusive = false)
                                }
                            )

                            characterDetailScreen(
                                onNavigateBack = {
                                    navController.navigateUp()
                                }
                            )

                            profileScreen(
                                onBackToLogin = {
                                    loggedIn = false
                                    navController.popBackStack(LoginDestination, inclusive = false)
                                }
                            )

                            locationScreen(
                                onLocationClick = { locationId ->
                                    navController.navigateToLocationDetailScreen(locationId)
                                }
                            )

                            locationDetailScreen(
                                onBackClick = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
