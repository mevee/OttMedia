package com.vee.musicapp.firebase

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.vee.musicapp.data.local.dao.LogsDao
import com.vee.musicapp.data.local.models.LogItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseAnalyticsHelper(private val context: Context, private val localStorage: LogsDao) {
    private val TAG = "FirebaseAnalyticsHelper"
    private val analytics = FirebaseAnalytics.getInstance(context)

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
        CoroutineScope(Dispatchers.IO).launch {
            val mainKey = (params?.getString("railId") ?: "") + (params?.getString("movieId") ?: "")
            val logItem = LogItem(
                mainKey = mainKey,
                railId = params?.getString("railId") ?: "",
                movieId = params?.getString("movieId") ?: "",
                eventName = eventName,
                movieName = params?.getString("movieName") ?: "",
                extra = params?.getString("extra") ?: ""
            )
            localStorage.insertItem(logItem)
        }
    }

    fun syncOfflineLogs() {
        if (isNetworkAvailable()) {
            CoroutineScope(Dispatchers.IO).launch {
                val logs = localStorage.getAllLogs()
                logs.forEach { log ->
                    val eventName = log.eventName
                    val params = Bundle()
                    params.putString("railId", log.railId)
                    params.putString("movieId", log.movieId)
                    params.putString("movieName", log.movieName)
                    if (!log.extra.isNullOrEmpty()) {
                        params.putString("extra", log.extra)
                    }
                    analytics.logEvent(eventName, params)
                    localStorage.deleteItems(log)
                }
            }
        }
    }
}
