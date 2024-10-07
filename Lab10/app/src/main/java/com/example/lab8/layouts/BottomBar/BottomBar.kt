package com.example.lab8.layouts.BottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomBarItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

val bottomBarItems = listOf(
    BottomBarItem("Characters", "characters", Icons.Default.Person),
    BottomBarItem("Locations", "locations", Icons.Default.Place), // Cambié "location" a "locations"
    BottomBarItem("Profile", "profile", Icons.Default.AccountCircle)
)
@Composable
fun BottomBar(
    navController: NavController,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    NavigationBar {
        bottomBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = { onItemSelected(index) }
            )
        }
    }
}
