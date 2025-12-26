package com.example.codelearnapp.data.sync

import android.content.Context
import androidx.work.*
import com.example.codelearnapp.data.local.AppDatabase
import com.example.codelearnapp.data.remote.FirestoreRepository
import java.util.concurrent.TimeUnit

class SyncManager(
    private val context: Context,
    private val database: AppDatabase,
    private val firestoreRepository: FirestoreRepository
) {

    fun scheduleSyncWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            15, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "sync_work",
                ExistingPeriodicWorkPolicy.KEEP,
                syncRequest
            )
    }

    fun syncNow() {
        val syncRequest = OneTimeWorkRequestBuilder<SyncWorker>()
            .build()

        WorkManager.getInstance(context)
            .enqueue(syncRequest)
    }
}