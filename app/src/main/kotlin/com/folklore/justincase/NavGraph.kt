package com.folklore.justincase

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route) {
            MainScreen(onNavigateToNew = { navController.navigate(Destination.Note.createRoute()) })
        }

        composable(Destination.Note.route) {
            NoteScreen(onBack = { navController.popBackStack() })
        }
    }
}
