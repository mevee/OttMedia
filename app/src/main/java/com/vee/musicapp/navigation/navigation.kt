package com.vee.musicapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.vee.musicapp.modules.home.HomeScreen
import com.vee.musicapp.modules.splash.SplashScreen
import com.vee.musicapp.util.LogEventType
import com.vee.musicapp.viewmodel.MovieViewModel
import com.vee.musicapp.viewmodel.SplashViewModel

@Composable
fun Navigation(homeViewModel: MovieViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Splash.routeNane) {
        composable(route = Routes.Home.routeNane) {
            val uiState = homeViewModel.uiState.collectAsState()
            HomeScreen(
                uiState,
                reloadHomeData = {
                    homeViewModel.loadHomeData(reloadAfterError = true)
                },
                onItemClicked = {railId, movie ->
//                    throw RuntimeException("Test Crash") // this line will trig crash and it will be reported in crashlytics
                    homeViewModel.logData(LogEventType.MovieCardVisited, data = movie, railId)
                },
                onItemScrolled = { railId, movie ->
                    homeViewModel.logData(LogEventType.MovieCardVisited, data = movie, railId)
                },
            )
        }
        composable(route = Routes.Splash.routeNane) {
            val detailsViewModel: SplashViewModel = viewModel()
            val splashText = detailsViewModel.splashText.collectAsState()
            SplashScreen(navController = navController, splashText.value)
        }
    }

}