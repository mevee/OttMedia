package com.vee.musicapp.firebase

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.vee.musicapp.pref.AppPref
import org.json.JSONObject

class FirebaseAnalyticsHelper(private val context: Context) {
    private val TAG = "FirebaseAnalyticsHelper"
    private val analytics = Firebase.analytics
    private val localStorage = AppPref(context)

    fun logEvent(eventName: String, params: Bundle? = null) {
        Log.d(TAG, "eventName$eventName::params${params.toString()}")
        Log.d(TAG, "isNetworkAvailable()${isNetworkAvailable()}")
        if (isNetworkAvailable()) {
            // 🔹 Log directly if network is available
            analytics.logEvent(eventName, params ?: Bundle())
        } else {
            // 🔹 Store event locally
            saveEventOffline(eventName, params)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        return activeNetwork != null
    }

    private fun saveEventOffline(eventName: String, params: Bundle?) {
        localStorage.saveEventOffline(eventName, params)
    }

    fun syncOfflineLogs() {
        if (isNetworkAvailable()) {
            val logs = localStorage.getSavedLog()
            logs.forEach { log ->
                val json = JSONObject(log)
                val eventName = json.getString("eventName")
                val paramsJson = json.optJSONObject("params")
                val params = Bundle().apply {
                    paramsJson?.let {
                        for (key in it.keys()) {
                            putString(key, it.getString(key))
                        }
                    }
                }
                analytics.logEvent(eventName, params)
            }
            localStorage.clearOfflineLogs()
        }
    }
}
