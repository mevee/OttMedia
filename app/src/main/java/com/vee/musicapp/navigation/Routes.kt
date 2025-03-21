package com.vee.musicapp.navigation

sealed class Routes(val routeNane:String) {
    data object Splash: Routes("splash-screen")
    data object Home: Routes("home-screen")
}
