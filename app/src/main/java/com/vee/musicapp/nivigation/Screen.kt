package com.vee.musicapp.nivigation

sealed class Screen(val routeNane:String) {
    data object Splash: Screen("splash-screen")
    data object Home: Screen("home-screen")
}
