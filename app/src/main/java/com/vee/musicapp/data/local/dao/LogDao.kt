package com.vee.musicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vee.musicapp.data.local.models.LogEntry

@Dao
interface LogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore if already exists
    suspend fun insertLog(log: LogEntry)

    @Query("SELECT * FROM logs WHERE railId = :railId")
    suspend fun getLogByRailId(railId: String): LogEntry?

    @Query("DELETE FROM logs WHERE railId = :railId")
    suspend fun deleteLog(railId: String)
}
