package com.vee.musicapp.firebase

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.vee.musicapp.data.models.DialogConfig
import com.vee.musicapp.util.AppConstants
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

class RemoteConfigHelper {
    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    private val configSettings: FirebaseRemoteConfigSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 0 // Fetch new values immediately (for debugging)
    }

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(
            mapOf(
                AppConstants.serviceLock to """{${AppConstants.remoteLock}: false, ${AppConstants.message}: ""}"""
            )
        )
    }

    suspend fun fetchConfig(): Boolean {
        return try {
            remoteConfig.fetchAndActivate().await()
        } catch (e: Exception) {
            Log.e("RemoteConfig", "Fetch failed", e)
            false
        }
    }

    fun getDialogConfig(): DialogConfig {
        val jsonString = remoteConfig.getString(AppConstants.serviceLock)
        return try {
            val jsonObject = JSONObject(jsonString)
            DialogConfig(
                lock = jsonObject.optBoolean(AppConstants.remoteLock, false),
                message = jsonObject.optString(AppConstants.message, AppConstants.defaultError)
            )
        } catch (e: Exception) {
            Log.e("RemoteConfig", "JSON Parsing Error", e)
            DialogConfig(false, "Error")
        }
    }

    fun getSplashText(onMessageReceived: (String) -> Unit) {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf(AppConstants.remoteSplashText to AppConstants.welcomeMessage))
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onMessageReceived.invoke(remoteConfig.getString(AppConstants.remoteSplashText))
            } else {
                onMessageReceived.invoke(AppConstants.welcomeMessage)
            }
        }
    }

}

