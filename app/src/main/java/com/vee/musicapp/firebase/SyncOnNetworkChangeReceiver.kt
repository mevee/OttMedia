package com.vee.musicapp.firebase

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class SyncOnNetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent?.action) {
            val analyticsHelper = FirebaseAnalyticsHelper(context)
            analyticsHelper.syncOfflineLogs()
        }
    }
}