package com.vee.musicapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashViewModel : ViewModel() {
    private val _splashText = MutableStateFlow("Welcome to OriginOtt")
    val splashText: StateFlow<String> = _splashText.asStateFlow()

}