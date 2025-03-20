package com.vee.musicapp.nivigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.vee.musicapp.modules.home.HomeScreen
import com.vee.musicapp.modules.splash.SplashScreen
import com.vee.musicapp.viewmodel.MovieViewModel
import com.vee.musicapp.viewmodel.SplashViewModel

@Composable
fun Navigation(homeViewModel: MovieViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.routeNane) {
        composable(route = Screen.Home.routeNane) {
            val uiState = homeViewModel.uiState.collectAsState()
            HomeScreen(
                uiState,
                reloadHomeData = {
                    homeViewModel.loadHomeData(reloadAfterError = true)
                },
            )
        }
        composable(route = Screen.Splash.routeNane) {
//            val viewModel = SplashViewModel()
            val detailsViewModel: SplashViewModel = viewModel()
            val splashText = detailsViewModel.splashText.collectAsState()
            SplashScreen(navController = navController, splashText.value)
        }
    }

}