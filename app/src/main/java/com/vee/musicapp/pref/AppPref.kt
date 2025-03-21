package com.vee.musicapp.pref

import android.content.Context
import android.os.Bundle
import org.json.JSONObject

class AppPref(private val context: Context) {
    private val pendingLogKEY = "pending_logs"
    private val sharedPrefs =
        context.getSharedPreferences(PrefConstants.logPrefKey, Context.MODE_PRIVATE)

    fun saveEventOffline(eventName: String, params: Bundle?) {
        val logs = sharedPrefs.getStringSet(pendingLogKEY, mutableSetOf())!!.toMutableSet()
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
        sharedPrefs.edit().putStringSet(pendingLogKEY, logs).apply()
    }

    fun getSavedLog(): MutableSet<String> {
        return sharedPrefs.getStringSet(pendingLogKEY, emptySet())!!.toMutableSet()
    }

    fun clearOfflineLogs() {
        sharedPrefs.edit().remove(pendingLogKEY).apply()
    }
}