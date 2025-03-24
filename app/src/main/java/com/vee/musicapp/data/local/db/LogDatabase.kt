package com.vee.musicapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vee.musicapp.data.local.dao.LogDao
import com.vee.musicapp.data.local.models.LogEntry
import com.vee.musicapp.data.local.models.LogItem
import com.vee.musicapp.data.local.dao.LogItemDao
import com.vee.musicapp.util.AppConstants

@Database(entities = [LogEntry::class, LogItem::class], version = 1)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao
    abstract fun logItemDao(): LogItemDao

    companion object {
        @Volatile
        private var INSTANCE: LogDatabase? = null
        fun getDatabase(context: Context): LogDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LogDatabase::class.java,
                    AppConstants.RoomDb.logDbName
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
