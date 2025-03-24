package com.vee.musicapp.firebase

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.vee.musicapp.data.local.db.LogDatabase


class SyncEventsWorker(private val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val logDb = LogDatabase.getDatabase(context).logDao()
        val helper = FirebaseAnalyticsHelper(context = context, localStorage = logDb)
        helper.syncOfflineLogs()
        return Result.success()
    }
}