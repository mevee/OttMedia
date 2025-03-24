package com.vee.musicapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "log_items")
data class LogItem(
    @PrimaryKey(autoGenerate = false)
    val mainKey: String,
    val railId: String,
    val movieId: String,
    val movieName: String,
    val eventName: String,
    val extra: String?,
    val timestamp: Long = System.currentTimeMillis()
)
