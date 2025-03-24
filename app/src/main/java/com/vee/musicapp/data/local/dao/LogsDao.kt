package com.vee.musicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vee.musicapp.data.local.models.LogItem

@Dao
interface LogsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore duplicates
    suspend fun insertItem(items: LogItem)

    //use limit a query to fetch the last 1000 items LIMIT 1000
    @Query("SELECT * FROM log_items ORDER BY timestamp DESC")
    suspend fun getAllLogs(): List<LogItem>

    @Delete
    suspend fun deleteItems(items: LogItem)
}
