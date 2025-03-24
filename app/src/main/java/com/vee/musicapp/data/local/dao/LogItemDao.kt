package com.vee.musicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vee.musicapp.data.local.models.LogItem

@Dao
interface LogItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore duplicates
    suspend fun insertItems(items: List<LogItem>)

    @Query("SELECT * FROM log_items WHERE railId = :railId")
    suspend fun getItemsByRailId(railId: String): List<LogItem>

    @Query("DELETE FROM log_items WHERE railId = :railId")
    suspend fun deleteItemsByRailId(railId: String)
}
