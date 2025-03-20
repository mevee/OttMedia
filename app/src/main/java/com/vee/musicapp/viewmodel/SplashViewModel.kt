package com.vee.musicapp.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.vee.musicapp.firebase.RemoteConfigHelper
import com.vee.musicapp.util.AppConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashViewModel : ViewModel() {
    private val remoteConfig = Firebase.remoteConfig
    private val _splashText = MutableStateFlow("Loading...")
    val splashText: StateFlow<String> = _splashText.asStateFlow()
    val remoteConfigHelper  =  RemoteConfigHelper()

    init {
        remoteConfigHelper.getSplashText(){
            _splashText.value = it
        }
    }

    private fun fetchRemoteConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0 // Fetch new values immediately (for debugging)
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf(AppConstants.remoteSplashText to "Welcome to MyApp!"))
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _splashText.value = remoteConfig.getString("splash_text")
            } else {
                _splashText.value = "Welcome to MyApp!!"
            }
        }
    }
}