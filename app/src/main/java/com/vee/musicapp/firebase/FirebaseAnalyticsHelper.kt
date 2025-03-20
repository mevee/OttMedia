package com.vee.musicapp.firebase

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.vee.musicapp.util.AppConstants
import org.json.JSONObject

class FirebaseAnalyticsHelper(private val context: Context) {

    private val analytics = Firebase.analytics
    private val sharedPrefs = context.getSharedPreferences(AppConstants.logPref, Context.MODE_PRIVATE)

    fun logEvent(eventName: String, params: Bundle? = null) {
        if (isNetworkAvailable()) {
            // 🔹 Log directly if network is available
            analytics.logEvent(eventName, params ?: Bundle())
        } else {
            // 🔹 Store event locally
            saveEventOffline(eventName, params)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        return activeNetwork != null
    }

    private fun saveEventOffline(eventName: String, params: Bundle?) {
        val logs = sharedPrefs.getStringSet("pending_logs", mutableSetOf())!!.toMutableSet()
        val eventJson = JSONObject().apply {
            put("eventName", eventName)
            params?.let { 
                val jsonParams = JSONObject()
                for (key in it.keySet()) {
                    jsonParams.put(key, it.get(key))
                }
                put("params", jsonParams)
            }
        }
        logs.add(eventJson.toString())
        sharedPrefs.edit().putStringSet("pending_logs", logs).apply()
    }

    fun syncOfflineLogs() {
        if (isNetworkAvailable()) {
            val logs = sharedPrefs.getStringSet("pending_logs", emptySet())!!.toMutableSet()
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
            sharedPrefs.edit().remove("pending_logs").apply()
        }
    }
}
