package com.example.turismoapp.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.turismoapp.feature.dollar.presentation.DollarScreen

import com.example.turismoapp.feature.movie.presentation.PopularMoviesScreen
import com.example.turismoapp.feature.profile.presentation.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Profile.route

    ) {


        composable(Screen.Home.route) {
            // HomeScreen()
        }
        composable(Screen.Profile.route) {
             ProfileScreen()
        }



            composable(Screen.Dollar.route) {
                DollarScreen()
        }

        composable(Screen.PopularMovies.route) { PopularMoviesScreen() }
    }
}
