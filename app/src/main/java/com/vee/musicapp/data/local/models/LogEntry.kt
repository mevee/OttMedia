package com.vee.musicapp.data.local.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
data class LogEntry(
    @PrimaryKey val railId: String,  // Ensures uniqueness
    val isSynced: Boolean = false    // Tracks sync status
)

@Entity(
    tableName = "log_items",
    primaryKeys = ["railId", "uid"], // Ensures uniqueness
    foreignKeys = [
        ForeignKey(
            entity = LogEntry::class,
            parentColumns = ["railId"],
            childColumns = ["railId"],
            onDelete = ForeignKey.CASCADE // Delete items if log is deleted
        )
    ]
)
data class LogItem(
    val railId: String,
    val uid: String,
    val name: String,
    val timestamp: Long = System.currentTimeMillis()

)
