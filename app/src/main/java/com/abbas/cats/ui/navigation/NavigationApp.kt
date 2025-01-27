package com.abbas.cats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abbas.cats.ui.CatDetailsScreen
import com.abbas.cats.ui.CatsScreen

val home = "home"
val detail = "detail"

@Composable
fun NavigationApp(modifier: Modifier) {
    val navController = rememberNavController() // Manages navigation stack
    NavHost(navController = navController, startDestination = home) {
        composable(home) {
            CatsScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(detail) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(home)
            }
            CatDetailsScreen(viewModel = hiltViewModel(parentEntry))
        }
    }
}