package com.vee.musicapp.data.local.db

import android.content.Context
 import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vee.musicapp.data.local.models.LogItem
import com.vee.musicapp.data.local.dao.LogsDao
import com.vee.musicapp.util.AppConstants

//@Database(
//    entities = [LogItem::class],
//    version = 1,
//    exportSchema = false
//)
//abstract class LogDatabase : RoomDatabase() {
//    abstract fun logDao(): LogsDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: LogDatabase? = null
//        fun getDatabase(context: Context): LogDatabase {
//            val tempInstance = INSTANCE
//
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    LogDatabase::class.java,
//                    AppConstants.RoomDb.logDbName
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}

@Database(entities = [LogItem::class], version = 1, exportSchema = false)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogsDao

    companion object {
        @Volatile
        private var INSTANCE: LogDatabase? = null

        fun getInstance(context: Context): LogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LogDatabase::class.java,
                    "log_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
