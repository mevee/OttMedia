package com.vee.musicapp.firebase

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SyncEventsWorker(private val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val helper = FirebaseAnalyticsHelper(context = context)
        return withContext(Dispatchers.IO) {
            try {
                helper.syncOfflineLogs()
                Result.success()
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                Result.retry()  // Retry on failure
            }
        }
    }
}