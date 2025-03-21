package com.vee.musicapp.firebase

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class SyncEventsWorker(private val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val helper = FirebaseAnalyticsHelper(context = context)
        helper.syncOfflineLogs()
        return Result.success()
    }
}